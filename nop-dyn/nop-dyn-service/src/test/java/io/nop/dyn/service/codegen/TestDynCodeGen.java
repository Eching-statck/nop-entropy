package io.nop.dyn.service.codegen;

import io.nop.api.core.annotations.autotest.NopTestConfig;
import io.nop.api.core.beans.ApiRequest;
import io.nop.api.core.beans.ApiResponse;
import io.nop.api.core.beans.PageBean;
import io.nop.api.core.util.FutureHelper;
import io.nop.autotest.junit.JunitBaseTestCase;
import io.nop.commons.type.StdSqlType;
import io.nop.core.reflect.bean.BeanTool;
import io.nop.core.resource.VirtualFileSystem;
import io.nop.dao.api.IDaoProvider;
import io.nop.dao.api.IEntityDao;
import io.nop.dyn.dao.NopDynDaoConstants;
import io.nop.dyn.dao.entity.NopDynEntity;
import io.nop.dyn.dao.entity.NopDynEntityMeta;
import io.nop.dyn.dao.entity.NopDynFunctionMeta;
import io.nop.dyn.dao.entity.NopDynModule;
import io.nop.dyn.dao.entity.NopDynPropMeta;
import io.nop.graphql.core.IGraphQLExecutionContext;
import io.nop.graphql.core.engine.IGraphQLEngine;
import io.nop.orm.IOrmTemplate;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@NopTestConfig(localDb = true, initDatabaseSchema = true)
public class TestDynCodeGen extends JunitBaseTestCase {

    @Inject
    IDaoProvider daoProvider;

    @Inject
    DynCodeGen codeGen;

    @Inject
    IOrmTemplate ormTemplate;

    @Inject
    IGraphQLEngine graphQLEngine;

    @Test
    public void testGen() {
        saveModule();

        ormTemplate.runInSession(() -> {
            codeGen.generateForAllModules();
            codeGen.reloadModel();

            IEntityDao<NopDynEntity> dao = daoProvider.dao("MyDynEntity");
            dao.findAll();

            IGraphQLExecutionContext gqlContext = graphQLEngine.newRpcContext(null, "MyDynEntity__findPage", ApiRequest.build(null));
            @SuppressWarnings("unchecked")
            ApiResponse<?> response = FutureHelper.syncGet(graphQLEngine.executeRpcAsync(gqlContext));
            assertEquals(true, response.isOk());
            PageBean<?> pageBean = BeanTool.castBeanToType(response.getData(), PageBean.class);
            assertEquals(0, pageBean.getTotal());

            gqlContext = graphQLEngine.newRpcContext(null, "MyDynEntity__myMethod", ApiRequest.build(null));
            response = FutureHelper.syncGet(graphQLEngine.executeRpcAsync(gqlContext));
            assertEquals(123, response.getData());

            assertTrue(VirtualFileSystem.instance().getResource("/app/demo/pages/MyDynEntity/main.page.yaml").exists());
        });
    }

    @Test
    public void testGenBizModel() {
        saveModule();

        ormTemplate.runInSession(() -> {
            codeGen.generateForAllModules();
            codeGen.reloadModel();

            NopDynFunctionMeta func = getFuncMeta("myMethod");
            func.setSource("return 321");
            ormTemplate.flushSession();

            codeGen.generateBizModel(func.getEntityMeta());

            IGraphQLExecutionContext gqlContext;

            gqlContext = graphQLEngine.newRpcContext(null, "MyDynEntity__myMethod", ApiRequest.build(null));
            ApiResponse<?> response = FutureHelper.syncGet(graphQLEngine.executeRpcAsync(gqlContext));
            //assertEquals(321, response.getData());
        });
    }

    private NopDynFunctionMeta getFuncMeta(String name) {
        IEntityDao<NopDynFunctionMeta> dao = daoProvider.daoFor(NopDynFunctionMeta.class);
        NopDynFunctionMeta example = new NopDynFunctionMeta();
        example.setName(name);
        return dao.findFirstByExample(example);
    }

    private void saveModule() {
        NopDynModule module = new NopDynModule();
        module.setModuleName("app-demo");
        module.setDisplayName("Demo Module");
        module.setStatus(NopDynDaoConstants.MODULE_STATUS_PUBLISHED);

        NopDynEntityMeta entityMeta = new NopDynEntityMeta();
        entityMeta.setEntityName("test.MyDynEntity");
        entityMeta.setDisplayName("My Dynamic Entity");
        entityMeta.setModule(module);
        entityMeta.setStatus(1);
        entityMeta.setIsExternal(false);

        entityMeta.setStoreType(NopDynDaoConstants.ENTITY_STORE_TYPE_VIRTUAL);

        NopDynPropMeta prop = addProp(entityMeta, "name", StdSqlType.VARCHAR, 100);
        prop.setDynPropMapping(NopDynEntity.PROP_NAME_nopName);

        addProp(entityMeta, "value", StdSqlType.INTEGER, 0);

        addFunc(entityMeta, "myMethod", "return 123", "Int");
        addFunc(entityMeta, "myMethod2", "<c:unit/>", "Any");

        module.getEntityMetas().add(entityMeta);

        daoProvider.daoFor(NopDynModule.class).saveEntity(module);
    }

    private NopDynPropMeta addProp(NopDynEntityMeta entityMeta, String propName, StdSqlType sqlType, int precision) {
        NopDynPropMeta propMeta = new NopDynPropMeta();
        propMeta.setPropId(1);
        propMeta.setPropName(propName);
        propMeta.setDisplayName(propName + " Display");
        propMeta.setPrecision(precision);
        propMeta.setIsMandatory(true);
        propMeta.setStatus(1);
        propMeta.setStdSqlType(sqlType.getName());
        entityMeta.getPropMetas().add(propMeta);
        return propMeta;
    }

    private void addFunc(NopDynEntityMeta entityMeta, String funcName, String source,
                         String gqlType) {
        NopDynFunctionMeta funcMeta = new NopDynFunctionMeta();
        funcMeta.setName(funcName);
        funcMeta.setDisplayName(funcName);
        funcMeta.setReturnGqlType(gqlType);
        funcMeta.setSource(source);
        funcMeta.setEntityMeta(entityMeta);
        funcMeta.setStatus(1);
        funcMeta.setFunctionType(NopDynDaoConstants.FUNCTION_TYPE_QUERY);
        entityMeta.getFunctionMetas().add(funcMeta);
    }
}
