//__XGEN_FORCE_OVERRIDE__
package io.nop.xlang.ast._gen;

import io.nop.xlang.ast.InExpression;
import io.nop.xlang.ast.XLangASTNode; //NOPMD NOSONAR - suppressed UnusedImports - Auto Gen Code

import io.nop.xlang.ast.XLangASTKind;
import io.nop.core.lang.json.IJsonHandler;
import io.nop.api.core.util.ProcessResult;
import java.util.function.Function;
import java.util.function.Consumer;


// tell cpd to start ignoring code - CPD-OFF
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S116","java:S3008","java:S1602",
        "PMD.UnnecessaryFullyQualifiedName","PMD.UnnecessaryImport","PMD.EmptyControlStatement"})
public abstract class _InExpression extends io.nop.xlang.ast.Expression {
    
    protected io.nop.xlang.ast.Expression left;
    
    protected io.nop.xlang.ast.Expression right;
    

    public _InExpression(){
    }

    
    public io.nop.xlang.ast.Expression getLeft(){
        return left;
    }

    public void setLeft(io.nop.xlang.ast.Expression value){
        checkAllowChange();
        if(value != null) value.setASTParent(this);
        
        this.left = value;
    }
    
    public io.nop.xlang.ast.Expression getRight(){
        return right;
    }

    public void setRight(io.nop.xlang.ast.Expression value){
        checkAllowChange();
        if(value != null) value.setASTParent(this);
        
        this.right = value;
    }
    

    public void validate(){
       super.validate();
     
          checkMandatory("left",getLeft());
       
          checkMandatory("right",getRight());
       
    }


    public InExpression newInstance(){
      return new InExpression();
    }

    @Override
    public InExpression deepClone(){
       InExpression ret = newInstance();
    ret.setLocation(getLocation());
    ret.setLeadingComment(getLeadingComment());
    ret.setTrailingComment(getTrailingComment());
    copyExtFieldsTo(ret);
    
                if(left != null){
                  
                          ret.setLeft(left.deepClone());
                      
                }
            
                if(right != null){
                  
                          ret.setRight(right.deepClone());
                      
                }
            
       return ret;
    }

    @Override
    public void forEachChild(Consumer<XLangASTNode> processor){
    
            if(left != null)
                processor.accept(left);
        
            if(right != null)
                processor.accept(right);
        
    }

    @Override
    public ProcessResult processChild(Function<XLangASTNode,ProcessResult> processor){
    
            if(left != null && processor.apply(left) == ProcessResult.STOP)
               return ProcessResult.STOP;
        
            if(right != null && processor.apply(right) == ProcessResult.STOP)
               return ProcessResult.STOP;
        
       return ProcessResult.CONTINUE;
    }

    @Override
    public boolean replaceChild(XLangASTNode oldChild, XLangASTNode newChild){
    
            if(this.left == oldChild){
               this.setLeft((io.nop.xlang.ast.Expression)newChild);
               return true;
            }
        
            if(this.right == oldChild){
               this.setRight((io.nop.xlang.ast.Expression)newChild);
               return true;
            }
        
        return false;
    }

    @Override
    public boolean removeChild(XLangASTNode child){
    
            if(this.left == child){
                this.setLeft(null);
                return true;
            }
        
            if(this.right == child){
                this.setRight(null);
                return true;
            }
        
    return false;
    }

    @Override
    public boolean isEquivalentTo(XLangASTNode node){
       if(this.getASTKind() != node.getASTKind())
          return false;
    InExpression other = (InExpression)node;
    
            if(!isNodeEquivalent(this.left,other.getLeft())){
               return false;
            }
        
            if(!isNodeEquivalent(this.right,other.getRight())){
               return false;
            }
        
        return true;
    }

    @Override
    public XLangASTKind getASTKind(){
       return XLangASTKind.InExpression;
    }

    protected void serializeFields(IJsonHandler json) {
        
                    if(left != null){
                      
                              json.put("left", left);
                          
                    }
                
                    if(right != null){
                      
                              json.put("right", right);
                          
                    }
                
    }

    @Override
    public void freeze(boolean cascade){
      super.freeze(cascade);
        
                if(left != null)
                    left.freeze(cascade);
                if(right != null)
                    right.freeze(cascade);
    }

}
 // resume CPD analysis - CPD-ON
