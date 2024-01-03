package io.nop.ioc.model._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [23:10:0:0]/nop/schema/beans.xdef <p>
 * ref local机制在spring4.0被废除
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _BeanRefValue extends io.nop.core.resource.component.AbstractComponentModel {
    
    /**
     *  
     * xml name: bean
     * 
     */
    private java.lang.String _bean ;
    
    /**
     *  
     * xml name: ioc:ignore-depends
     * 
     */
    private boolean _iocIgnoreDepends  = false;
    
    /**
     *  
     * xml name: ioc:optional
     * 
     */
    private boolean _iocOptional  = false;
    
    /**
     * 
     * xml name: bean
     *  
     */
    
    public java.lang.String getBean(){
      return _bean;
    }

    
    public void setBean(java.lang.String value){
        checkAllowChange();
        
        this._bean = value;
           
    }

    
    /**
     * 
     * xml name: ioc:ignore-depends
     *  
     */
    
    public boolean isIocIgnoreDepends(){
      return _iocIgnoreDepends;
    }

    
    public void setIocIgnoreDepends(boolean value){
        checkAllowChange();
        
        this._iocIgnoreDepends = value;
           
    }

    
    /**
     * 
     * xml name: ioc:optional
     *  
     */
    
    public boolean isIocOptional(){
      return _iocOptional;
    }

    
    public void setIocOptional(boolean value){
        checkAllowChange();
        
        this._iocOptional = value;
           
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
        
        out.put("bean",this.getBean());
        out.put("iocIgnoreDepends",this.isIocIgnoreDepends());
        out.put("iocOptional",this.isIocOptional());
    }
}
 // resume CPD analysis - CPD-ON
