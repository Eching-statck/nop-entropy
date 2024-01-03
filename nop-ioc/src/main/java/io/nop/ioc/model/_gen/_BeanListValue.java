package io.nop.ioc.model._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [32:10:0:0]/nop/schema/beans.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _BeanListValue extends io.nop.ioc.model.BeanCollectionValue {
    
    /**
     *  
     * xml name: list-class
     * 
     */
    private java.lang.String _listClass ;
    
    /**
     * 
     * xml name: list-class
     *  
     */
    
    public java.lang.String getListClass(){
      return _listClass;
    }

    
    public void setListClass(java.lang.String value){
        checkAllowChange();
        
        this._listClass = value;
           
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
        
        out.put("listClass",this.getListClass());
    }
}
 // resume CPD analysis - CPD-ON
