# 堆栈式任务流

TaskFlow是采用堆栈结构的轻量级工作流。它具有如下功能：

1. 一个Task分解为多个Step
2. Step允许异步执行或者同步执行
3. Step返回TaskStepResult，其中可以通过nextStepId指定跳转到指定步骤
4. 缺省情况下执行完步骤A后会继续执行步骤的下一个兄弟节点

## 任务步骤接口

````java 
interface ITaskStep {
    String getStepId();

    TaskStepResult execute(int runId, ITaskStepState parentState, ITaskContext taskContext);
} 
````

* stepId: 每个步骤具有唯一的定义id，它在整个流程图定义中唯一
* runId: 步骤每次执行都产生一个新的runId。例如循环执行10次，则产生10个不同的runId，通过runId可以区分步骤多次执行产生的不同实例。id=stepId+runId
* parentState: 步骤的执行实例记录了父子关系，从而可以根据这些状态记录恢复出调用堆栈结构。
* taskContext: 任务执行过程中的全局上下文。每个步骤的input都从taskContext中读取数据，步骤执行完毕后再通过output修改taskContext中的全局变量

步骤之间通过全局的taskContext来交换信息，相当于是一种黑板模式。

## 步骤通用能力

所有的任务步骤都具有一些通用的属性，例如执行条件检查、失败重试、触发次数限制、超时时间等。对这些通用属性的处理逻辑如下：

1. 在全局scope中检查when条件是否满足，不满足条件则直接跳过步骤
2. 根据input配置初始化输入变量，将它们设置到步骤scope中
3. 注册超时时间，超时时间到达的时候取消整个step的执行，step的状态转换为TIMEOUT
4. 注册重试策略，如果后续执行失败，则按照重试策略重试
5. 检查throttle和rate-limit配置，对请求进行限速
6. 执行decorator
7. 执行单次尝试，如果失败，则执行catch处理。
8. 步骤执行完毕后，根据output设置将步骤scope中的变量保存到全局scope中