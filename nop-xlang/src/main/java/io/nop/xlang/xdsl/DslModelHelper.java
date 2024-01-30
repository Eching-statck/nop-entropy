/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.nop.xlang.xdsl;

import io.nop.api.core.util.Guard;
import io.nop.api.core.util.IComponentModel;
import io.nop.commons.util.StringHelper;
import io.nop.core.lang.eval.DisabledEvalScope;
import io.nop.core.lang.xml.XNode;
import io.nop.core.model.object.DynamicObject;
import io.nop.core.reflect.IClassModel;
import io.nop.core.reflect.IFunctionModel;
import io.nop.core.reflect.ReflectionManager;
import io.nop.core.resource.IResource;
import io.nop.core.resource.IResourceObjectLoader;
import io.nop.xlang.xdef.IXDefNode;
import io.nop.xlang.xdsl.json.DslModelToXNodeTransformer;
import io.nop.xlang.xdsl.json.DslXNodeToJsonTransformer;
import io.nop.xlang.xmeta.IObjMeta;
import io.nop.xlang.xmeta.ISchema;
import io.nop.xlang.xmeta.SchemaLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DslModelHelper {
    static final Logger LOG = LoggerFactory.getLogger(DslModelHelper.class);

    public static DynamicObject loadDslModelAsJson(IResource resource, boolean forEditor) {
        GenericDslParser parser = new GenericDslParser().forEditor(forEditor);
        return parser.parseFromResource(resource);
    }

    /**
     * 解析dsl模型对象。要求模型文件的根节点上通过x:schema属性来引入元模型定义
     *
     * @param resource 模型文件路径
     */
    public static IComponentModel loadDslModel(IResource resource) {
        return new DslModelParser().parseFromResource(resource);
    }

    public static IComponentModel parseDslModelNode(String xdefPath, XNode node){
        return new DslModelParser(xdefPath).parseFromNode(node);
    }

    /**
     * 将Dsl模型对象或者json对象按照xdef定义转换为XML格式后保存到模型文件中
     *
     * @param xdefPath xdef元模型定义路径
     * @param model    模型对象
     * @param resource 模型文件路径
     */
    public static void saveDslModel(String xdefPath, Object model, IResource resource) {
        IObjMeta objMeta = SchemaLoader.loadXMeta(xdefPath);
        XNode node = new DslModelToXNodeTransformer(objMeta).transformToXNode(model);
        node.saveToResource(resource, null);
    }

    public static XNode dslModelToXNode(String xdefPath, Object model) {
        IObjMeta objMeta = SchemaLoader.loadXMeta(xdefPath);
        XNode node = new DslModelToXNodeTransformer(objMeta).transformToXNode(model);
        return node;
    }

    public static XNode dslJsonToNode(ISchema schema, Object model) {
        return new DslModelToXNodeTransformer(null).transformObj(schema, model);
    }

    public static List<XNode> dslJsonListToNodeList(ISchema schema, List<?> list) {
        if (list == null || list.isEmpty())
            return Collections.emptyList();
        return list.stream().map(model -> dslJsonToNode(schema, model)).collect(Collectors.toList());
    }

    public static Object dslNodeToJson(IXDefNode defNode, XNode node) {
        return new DslXNodeToJsonTransformer(true, null, null).transformToObject(defNode, node);
    }

    public static String getXdefPath(Object model, String defaultXdefPath) {
        String xdefPath = defaultXdefPath;
        if (model instanceof IXDslModel) {
            xdefPath = ((IXDslModel) model).getXdslSchema();
            if (StringHelper.isEmpty(xdefPath))
                xdefPath = defaultXdefPath;
        }
        return xdefPath;
    }

    static Class<?> g_excelModelLoaderClass;

    public static void registerExcelModelLoaderClass(Class<?> clazz) {
        g_excelModelLoaderClass = clazz;
    }

    public static boolean supportExcelModelLoader() {
        if (g_excelModelLoaderClass != null)
            return true;

        try {
            ReflectionManager.instance().loadClassModel(XDslConstants.EXCEL_MODEL_LOADER_CLASS);
            return true;
        } catch (Exception e) {
            LOG.warn("nop.xlang.not-support-excel-model-loader:missing-lib={}", "nop-ooxml-xlsx.jar");
            return false;
        }

    }

    public static IResourceObjectLoader<IComponentModel> newExcelModelLoader(String impModelPath) {
        IClassModel classModel;
        if (g_excelModelLoaderClass != null) {
            classModel = ReflectionManager.instance().getClassModel(g_excelModelLoaderClass);
        } else {
            classModel = ReflectionManager.instance().loadClassModel(XDslConstants.EXCEL_MODEL_LOADER_CLASS);
        }
        IFunctionModel fn = classModel.getConstructor(new Class[]{String.class});
        Guard.checkEquals(1, fn.getArgCount());
        return (IResourceObjectLoader<IComponentModel>) fn.invoke(null, new String[]{impModelPath}, DisabledEvalScope.INSTANCE);
    }
}
