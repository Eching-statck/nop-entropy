package test.entity._gen;

import io.nop.orm.model.IEntityModel;
import io.nop.orm.support.DynamicOrmEntity;
import io.nop.orm.support.OrmEntitySet; //NOPMD - suppressed UnusedImports - Auto Gen Code
import io.nop.orm.IOrmEntitySet; //NOPMD - suppressed UnusedImports - Auto Gen Code
import io.nop.api.core.convert.ConvertHelper;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

import test.entity.TestCompositeOneToOneSub;

// tell cpd to start ignoring code - CPD-OFF
/**
 *  : TEST_COMPOSITE_ONE_TO_ONE_SUB
 */
@SuppressWarnings({"PMD.UselessOverridingMethod","PMD.UnusedLocalVariable","java:S3008","java:S1602","java:S1128","java:S1161",
        "PMD.UnnecessaryFullyQualifiedName","PMD.EmptyControlStatement","java:S116","java:S115","java:S101","java:S3776"})
public class _TestCompositeOneToOneSub extends DynamicOrmEntity{
    
    /* : FLD3 VARCHAR */
    public static final String PROP_NAME_fldA = "fldA";
    public static final int PROP_ID_fldA = 5;
    
    /* : FLD4 VARCHAR */
    public static final String PROP_NAME_fldB = "fldB";
    public static final int PROP_ID_fldB = 6;
    
    /* : INT_VALUE INTEGER */
    public static final String PROP_NAME_intValue = "intValue";
    public static final int PROP_ID_intValue = 7;
    

    private static int _PROP_ID_BOUND = 8;

    
    /* relation:  */
    public static final String PROP_NAME_main = "main";
    

    protected static final List<String> PK_PROP_NAMES = Arrays.asList(PROP_NAME_fldA,PROP_NAME_fldB);
    protected static final int[] PK_PROP_IDS = new int[]{PROP_ID_fldA,PROP_ID_fldB};

    private static final String[] PROP_ID_TO_NAME = new String[8];
    private static final Map<String,Integer> PROP_NAME_TO_ID = new HashMap<>();
    static{
      
          PROP_ID_TO_NAME[PROP_ID_fldA] = PROP_NAME_fldA;
          PROP_NAME_TO_ID.put(PROP_NAME_fldA, PROP_ID_fldA);
      
          PROP_ID_TO_NAME[PROP_ID_fldB] = PROP_NAME_fldB;
          PROP_NAME_TO_ID.put(PROP_NAME_fldB, PROP_ID_fldB);
      
          PROP_ID_TO_NAME[PROP_ID_intValue] = PROP_NAME_intValue;
          PROP_NAME_TO_ID.put(PROP_NAME_intValue, PROP_ID_intValue);
      
    }

    
    /* : FLD3 */
    private java.lang.String _fldA;
    
    /* : FLD4 */
    private java.lang.String _fldB;
    
    /* : INT_VALUE */
    private java.lang.Integer _intValue;
    

    public _TestCompositeOneToOneSub(){
        // for debug
    }

    protected TestCompositeOneToOneSub newInstance(){
       return new TestCompositeOneToOneSub();
    }

    @Override
    public TestCompositeOneToOneSub cloneInstance() {
        TestCompositeOneToOneSub entity = newInstance();
        orm_forEachInitedProp((value, propId) -> {
            entity.onInitProp(propId);
        });
        return entity;
    }

    @Override
    public String orm_entityName() {
      // 如果存在实体模型对象，则以模型对象上的设置为准
      IEntityModel entityModel = orm_entityModel();
      if(entityModel != null)
          return entityModel.getName();
      return "test.entity.TestCompositeOneToOneSub";
    }

    @Override
    public int orm_propIdBound(){
      IEntityModel entityModel = orm_entityModel();
      if(entityModel != null)
          return entityModel.getPropIdBound();
      return _PROP_ID_BOUND;
    }

    @Override
    public Object orm_id() {
    
        return buildCompositeId(PK_PROP_NAMES,PK_PROP_IDS);
     
    }

    @Override
    public boolean orm_isPrimary(int propId) {
        
            return propId == PROP_ID_fldA || propId == PROP_ID_fldB;
          
    }

    @Override
    public String orm_propName(int propId) {
        if(propId >= PROP_ID_TO_NAME.length)
            return super.orm_propName(propId);
        String propName = PROP_ID_TO_NAME[propId];
        if(propName == null)
           return super.orm_propName(propId);
        return propName;
    }

    @Override
    public int orm_propId(String propName) {
        Integer propId = PROP_NAME_TO_ID.get(propName);
        if(propId == null)
            return super.orm_propId(propName);
        return propId;
    }

    @Override
    public Object orm_propValue(int propId) {
        switch(propId){
        
            case PROP_ID_fldA:
               return getFldA();
        
            case PROP_ID_fldB:
               return getFldB();
        
            case PROP_ID_intValue:
               return getIntValue();
        
           default:
              return super.orm_propValue(propId);
        }
    }

    

    @Override
    public void orm_propValue(int propId, Object value){
        switch(propId){
        
            case PROP_ID_fldA:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_fldA));
               }
               setFldA(typedValue);
               break;
            }
        
            case PROP_ID_fldB:{
               java.lang.String typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toString(value,
                       err-> newTypeConversionError(PROP_NAME_fldB));
               }
               setFldB(typedValue);
               break;
            }
        
            case PROP_ID_intValue:{
               java.lang.Integer typedValue = null;
               if(value != null){
                   typedValue = ConvertHelper.toInteger(value,
                       err-> newTypeConversionError(PROP_NAME_intValue));
               }
               setIntValue(typedValue);
               break;
            }
        
           default:
              super.orm_propValue(propId,value);
        }
    }

    @Override
    public void orm_internalSet(int propId, Object value) {
        switch(propId){
        
            case PROP_ID_fldA:{
               onInitProp(propId);
               this._fldA = (java.lang.String)value;
               orm_id(); // 如果是设置主键字段，则触发watcher
               break;
            }
        
            case PROP_ID_fldB:{
               onInitProp(propId);
               this._fldB = (java.lang.String)value;
               orm_id(); // 如果是设置主键字段，则触发watcher
               break;
            }
        
            case PROP_ID_intValue:{
               onInitProp(propId);
               this._intValue = (java.lang.Integer)value;
               
               break;
            }
        
           default:
              super.orm_internalSet(propId,value);
        }
    }

    
    /**
     * : FLD3
     */
    public java.lang.String getFldA(){
         onPropGet(PROP_ID_fldA);
         return _fldA;
    }

    /**
     * : FLD3
     */
    public void setFldA(java.lang.String value){
        if(onPropSet(PROP_ID_fldA,value)){
            this._fldA = value;
            internalClearRefs(PROP_ID_fldA);
            orm_id();
        }
    }
    
    /**
     * : FLD4
     */
    public java.lang.String getFldB(){
         onPropGet(PROP_ID_fldB);
         return _fldB;
    }

    /**
     * : FLD4
     */
    public void setFldB(java.lang.String value){
        if(onPropSet(PROP_ID_fldB,value)){
            this._fldB = value;
            internalClearRefs(PROP_ID_fldB);
            orm_id();
        }
    }
    
    /**
     * : INT_VALUE
     */
    public java.lang.Integer getIntValue(){
         onPropGet(PROP_ID_intValue);
         return _intValue;
    }

    /**
     * : INT_VALUE
     */
    public void setIntValue(java.lang.Integer value){
        if(onPropSet(PROP_ID_intValue,value)){
            this._intValue = value;
            internalClearRefs(PROP_ID_intValue);
            
        }
    }
    
    /**
     * 
     */
    public test.entity.TestCompositeOneToOneMain getMain(){
       return (test.entity.TestCompositeOneToOneMain)internalGetRefEntity(PROP_NAME_main);
    }

    public void setMain(test.entity.TestCompositeOneToOneMain refEntity){
   
           if(refEntity == null){
           
                   this.setFldA(null);
               
                   this.setFldB(null);
               
           }else{
           internalSetRefEntity(PROP_NAME_main, refEntity,()->{
           
                           this.setFldA(refEntity.getFldA());
                       
                           this.setFldB(refEntity.getFldB());
                       
           });
           }
       
    }
       
}
// resume CPD analysis - CPD-ON
