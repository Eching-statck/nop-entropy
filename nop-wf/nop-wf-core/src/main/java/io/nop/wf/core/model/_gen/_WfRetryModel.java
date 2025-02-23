package io.nop.wf.core.model._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;
import io.nop.wf.core.model.WfRetryModel;
import io.nop.commons.util.ClassHelper;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [215:14:0:0]/nop/schema/wf/wf.xdef <p>
 * 执行source的时候如果发生异常，则可以重试
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _WfRetryModel extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: exception-filter
     * 上下文环境中存在$exception变量，返回false表示异常不可被恢复，不能继续重试
     */
    private io.nop.core.lang.eval.IEvalPredicate _exceptionFilter ;
    
    /**
     *  
     * xml name: exponentialDelay
     * 
     */
    private boolean _exponentialDelay  = true;
    
    /**
     *  
     * xml name: maxRetryCount
     * 
     */
    private int _maxRetryCount  = 0;
    
    /**
     *  
     * xml name: maxRetryDelay
     * 
     */
    private int _maxRetryDelay  = 0;
    
    /**
     *  
     * xml name: retryDelay
     * 
     */
    private int _retryDelay  = 0;
    
    /**
     * 
     * xml name: exception-filter
     *  上下文环境中存在$exception变量，返回false表示异常不可被恢复，不能继续重试
     */
    
    public io.nop.core.lang.eval.IEvalPredicate getExceptionFilter(){
      return _exceptionFilter;
    }

    
    public void setExceptionFilter(io.nop.core.lang.eval.IEvalPredicate value){
        checkAllowChange();
        
        this._exceptionFilter = value;
           
    }

    
    /**
     * 
     * xml name: exponentialDelay
     *  
     */
    
    public boolean isExponentialDelay(){
      return _exponentialDelay;
    }

    
    public void setExponentialDelay(boolean value){
        checkAllowChange();
        
        this._exponentialDelay = value;
           
    }

    
    /**
     * 
     * xml name: maxRetryCount
     *  
     */
    
    public int getMaxRetryCount(){
      return _maxRetryCount;
    }

    
    public void setMaxRetryCount(int value){
        checkAllowChange();
        
        this._maxRetryCount = value;
           
    }

    
    /**
     * 
     * xml name: maxRetryDelay
     *  
     */
    
    public int getMaxRetryDelay(){
      return _maxRetryDelay;
    }

    
    public void setMaxRetryDelay(int value){
        checkAllowChange();
        
        this._maxRetryDelay = value;
           
    }

    
    /**
     * 
     * xml name: retryDelay
     *  
     */
    
    public int getRetryDelay(){
      return _retryDelay;
    }

    
    public void setRetryDelay(int value){
        checkAllowChange();
        
        this._retryDelay = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.putNotNull("exceptionFilter",this.getExceptionFilter());
        out.putNotNull("exponentialDelay",this.isExponentialDelay());
        out.putNotNull("maxRetryCount",this.getMaxRetryCount());
        out.putNotNull("maxRetryDelay",this.getMaxRetryDelay());
        out.putNotNull("retryDelay",this.getRetryDelay());
    }

    public WfRetryModel cloneInstance(){
        WfRetryModel instance = newInstance();
        this.copyTo(instance);
        return instance;
    }

    protected void copyTo(WfRetryModel instance){
        super.copyTo(instance);
        
        instance.setExceptionFilter(this.getExceptionFilter());
        instance.setExponentialDelay(this.isExponentialDelay());
        instance.setMaxRetryCount(this.getMaxRetryCount());
        instance.setMaxRetryDelay(this.getMaxRetryDelay());
        instance.setRetryDelay(this.getRetryDelay());
    }

    protected WfRetryModel newInstance(){
        return (WfRetryModel) ClassHelper.newInstance(getClass());
    }
}
 // resume CPD analysis - CPD-ON
