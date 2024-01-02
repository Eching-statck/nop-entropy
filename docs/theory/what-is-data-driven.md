# 什么是数据驱动？它和模型驱动、领域驱动、元数据驱动、DSL驱动之间有什么区别？

现在很多低代码平台都宣称自己的平台是数据驱动的，对此有人在群里提出一个疑问：什么是数据？表单不是数据吗？元数据也是数据啊？整个程序不是数据吗？既然所有这些都是数据，甚至Java程序也可以看作数据，数据驱动的说法还有什么意义呢？

XX Driven是软件工程领域的常见黑话之一，翻译过来就是某某驱动，替换一下XX，我们就得到了数据驱动、模型驱动、领域驱动、元数据驱动、DSL驱动等等这一大堆的驱动。一个很自然的疑问是，这些不同的驱动之间有什么区别？有什么必要人为制造出这么多不同的概念？

在本文中，我将结合Nop平台中的具体实践解释一下相关的概念。

# 一. 数据驱动什么？

数据驱动可以看作是**由数据来决定**。**所谓的驱动本质上就是在某个抽象层次上进行解释运行**。比如说，我们表达逻辑的时候需要执行判断和循环，如果在我们事先实现的函数中并没有直接表达具体什么时候判断以及什么时候循环，而是根据外部传入的某种数据来决定，那么这就看作是某种形式的数据驱动。换个角度说，我们编写的函数可以看作是某种虚拟机，它读取数据并在某种抽象层面上将这些数据作为程序代码来进行解释执行。

举几个具体的数据驱动的例子，  

1. 一般的代码生成器具体要生成哪些文件，这些文件的目录结构是什么是写在控制代码中的，比如有一个MyBatisCodeGenerator类。而Nop平台中的数据驱动的代码生成器，它的结构是一个通用的XCodeGenerator + 可以自由组织的代码生成模板，由模板数据来驱动XCodeGenerator来生成代码。具体判断某个文件是否生成，以及如何循环可以通过模板路径来表达 ```  
   /{packageModel.name}/{enabledWeb}{entityModel.name}BizModel.java  
   ```  
   以上路径形式表示packageModel和entityModel两层循环，同时enabledWeb为true的时候才生成对应的BizModel的java类。 详细介绍可以参见[数据驱动的差量化代码生成器](https://zhuanlan.zhihu.com/p/540022264)

2. 在一般的单元测试中具体的测试数据准备和测试结果校验工作都需要在代码中编码进行。Nop平台中的数据驱动的自动化测试框架采用的模式是一个通用的自动化测试引擎 + 一组json和csv数据，然后自动初始化数据库表，并自动进行结果校验。具体的测试用例代码退化为一个简单的函数调用。  
   
   ```
   request = input("request.json"); 
   response = myService.myMethod(request); 
   output("response.json", response); 
   ```
   
   在用例录制阶段NopAutoTest框架会自动记录response为json数据，并自动将其中的变量替换为变量名，在验证阶段会自动比较录制结果与执行结果是否匹配。  详细介绍参见[低代码平台中的自动化测试](https://zhuanlan.zhihu.com/p/569315603)



# 二. 什么不是数据驱动？

从某种意义上说，所有的程序都是数据驱动的。因为二进制代码确实就是一种数据，它驱动通用的图灵机实现所有可能的计算（图灵完备）。只不过一般情况下我们说的数据驱动是通过**自定义的某种形式的数据**来驱动特定的业务逻辑（**仅能解决某些特定业务问题的计算模型**），而不是驱动通用的图灵机计算模型。每当我们通过一组函数隐式的定义一个特定的计算模型，它可以根据传入的参数数据执行不同的逻辑处理过程，而且这组参数数据可以脱离我们的函数被独立的理解（**在语义层面构成某种独立性和完整性**），就相当于引入一种新的数据驱动。而我们一般的开发都是面向通用图灵机模型，所以我们不说它是数据驱动而已。

换句话说，`数据驱动 = 领域驱动 = 元数据驱动 = 模型驱动 = DSL`，这些概念的本质是一样的，本质上就是需要建立某种特定领域内的、非通用的逻辑模型而已。

但是在实际使用场景中，这些概念所强调的重点也有所不同。

* 模型驱动往往强调模型的技术中立性，同样的模型表达可以对应多种不同的具体实现。例如模型驱动架构（MDA）区分出了平台无关模型（PIM）和平台相关模型（PSM）。也有些场景会强调模型的可视化设计，**模型可能被保存为二进制形式，无法被直接理解**。

* DSL一般强调领域特定逻辑的文本表达形式，**可以直观的阅读、编写DSL文本**，不一定会为DSL配备对应的可视化编辑工具。（Nop平台根据可逆计算原理，强调DSL文本和可视化编辑不过是同一模型信息的两种信息等价的展现形式，可以在两者之间自动建立双向可逆转换，自动根据某种设计器定义生成对应的可视化设计器，也就是说并不需要手工为每一个DSL都特殊编写对应的设计工具，这项工作可以统一实现，具体做法参见[nop-idea-plugin插件](https://gitee.com/canonical-entropy/nop-entropy/tree/master/nop-idea-plugin)）

* 元数据驱动在目前的实践中一般只包含数据结构和数据类型定义。元数据号称是**描述数据的数据**，所以它往往被看作是程序语言中类型概念的一种扩展表达形式，例如数据库中表结构定义是针对表数据的元数据。很多的DSL都包含完整的逻辑函数定义以及自定义函数、外部函数库等逻辑抽象机制，而元数据的实践中一般这些动态逻辑的支持都比较弱。此外，将DSL解释为描述数据的数据也会让一般人感到困惑。

* 领域驱动强调的是我们在业务表达时所使用的概念要尽量贴近用户所理解的领域概念，而不是程序员所熟悉的技术概念，类似于我们要定义一种针对当前业务领域的DSL（领域特定语言）。但是在实践中，真正定义一种概念完备的语言需要花费很高的成本，而且真正的语言要求具有丰富的语法规则，各类语法要素具有丰富的可组合模式，相对来说很少有人能够有能力进行组合模式的抽象。所以，在实践中，**领域驱动中的所谓领域语言往往退化为一组领域专用的词汇表（术语表）**，然后这些概念的组合是通过少量流程函数以很模糊的方式进行串接，很少有人系统化的去考虑概念之间组合规则的完备性等。（所谓的语言是由词汇和语法组合规则来共同定义的，而领域驱动往往只有简单领域词汇和少量拍脑袋定义的服务函数）。

* 数据驱动原则上包含元数据驱动、只不过在一般人的概念中程序是由程序员编写的，而数据是由客户录入的，是**客户可以理解并且需要客户进行理解**，并继而进行管理的。所以，数据驱动一般需要把数据整理成客户容易理解的形式，并且在操作层面尽量简化。

# 三. Nop平台中的数据驱动

Nop平台系统化的应用了可逆计算原理，也就是借助`Y=F(X)+Delta`这种通用的计算模式，将大量问题转化为使用DSL来定义和解决。

```
App = Delta x-extends Generator<DSL> 
```

Nop平台所提供的是一种所谓的面向语言（Language Oriented Programming）的编程范式，即在解决业务问题之前，我们先定义一个针对当前业务领域的DSL，然后再用这个DSL去解决业务问题。

Nop平台中的设计并不是针对某个单一的、低代码专用的DSL，而是系统化的提供一种创建新的DSL，并实现不同DSL之间无缝融合的技术方案。

```
// 横向分解，产生多个DSL
App = Delta +  G1<DSL1> + G2<DSL2> + ...
// 深度分解，产生多个DSL
App = Delta + G1<Delta2 + G2<Delta3 + G3<DSL3>>> 
```

大量的DSL构成DSL森林，然后协同解决问题。

在日常的开发工作中，我们大量的开发工作并不是在编写具有本质性业务价值的业务逻辑，而是**在编写各种业务无关的形式转换逻辑**。比如说，将数据导出为Excel格式，将Excel格式导入到数据库中，将前端数据对象翻译为SQL查询条件等。**我们一直在忙于解决形式问题，而不是在解决业务问题**。

可逆计算在理论层面指明了DSL之间相互转换、相互融合所需的技术路线。Nop平台依据可逆计算原理根据元模型定义自动推导得到前端可视化展现和可视化设计器，自动实现数据查询对象与SQL查询条件的转换，自动实现数据对象与Excel文件之间的双向转换等。

Nop平台通过在各个层面引入大量共享元模型、可以协同工作的DSL，借助于XPL模板语言的抽象能力实现逻辑结构的模板化（逻辑流编排），将元数据、元模型的应用推进到一个新的高度，并且为领域驱动设计（DDD）寻找到一种很自然的基于模型驱动方式的实现方案。