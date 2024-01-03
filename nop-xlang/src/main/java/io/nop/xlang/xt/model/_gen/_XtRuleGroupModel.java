package io.nop.xlang.xt.model._gen;

import io.nop.commons.collections.KeyedList; //NOPMD NOSONAR - suppressed UnusedImports - Used for List Prop
import io.nop.core.lang.json.IJsonHandler;



// tell cpd to start ignoring code - CPD-OFF
/**
 * generate from [37:6:0:0]/nop/schema/xt.xdef <p>
 * 
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable",
    "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S101","java:S1128","java:S1161"})
public abstract class _XtRuleGroupModel extends io.nop.xlang.xt.model.XtRuleModel {
    
    /**
     *  
     * xml name: 
     * 
     */
    private java.util.List<io.nop.xlang.xt.model.XtRuleModel> _body = java.util.Collections.emptyList();
    
    /**
     * 
     * xml name: 
     *  
     */
    
    public java.util.List<io.nop.xlang.xt.model.XtRuleModel> getBody(){
      return _body;
    }

    
    public void setBody(java.util.List<io.nop.xlang.xt.model.XtRuleModel> value){
        checkAllowChange();
        
        this._body = value;
           
    }

    

    @Override
    public void freeze(boolean cascade){
        if(frozen()) return;
        super.freeze(cascade);

        if(cascade){ //NOPMD - suppressed EmptyControlStatement - Auto Gen Code
        
           this._body = io.nop.api.core.util.FreezeHelper.deepFreeze(this._body);
            
        }
    }

    @Override
    protected void outputJson(IJsonHandler out){
        super.outputJson(out);
        
        out.put("body",this.getBody());
    }
}
 // resume CPD analysis - CPD-ON
