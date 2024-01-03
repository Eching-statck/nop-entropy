
package io.nop.dyn.service.entity;

import io.nop.api.core.annotations.biz.BizModel;
import io.nop.api.core.annotations.biz.BizMutation;
import io.nop.api.core.annotations.biz.BizQuery;
import io.nop.api.core.annotations.core.Name;
import io.nop.biz.crud.CrudBizModel;
import io.nop.biz.crud.EntityData;
import io.nop.commons.util.StringHelper;
import io.nop.core.context.IServiceContext;
import io.nop.dyn.dao.entity.NopDynPage;

import java.util.Map;

@BizModel("NopDynPage")
public class NopDynPageBizModel extends CrudBizModel<NopDynPage> {
    public NopDynPageBizModel() {
        setEntityName(NopDynPage.class.getName());
    }


    @Override
    protected void defaultPrepareSave(EntityData<NopDynPage> entityData, IServiceContext context) {
        super.defaultPrepareSave(entityData, context);

        if (StringHelper.isEmpty(entityData.getEntity().getPageContent())) {
            entityData.getEntity().setPageContent("{\"type\":\"page\"}");
        }
    }

    @BizQuery
    public Map<String, Object> getPageJson(@Name("id") String id, IServiceContext context) {
        NopDynPage page = get(id, false, context);
        return page.getPageContentComponent().get_jsonMap();
    }

    @BizMutation
    public void savePageJson(@Name("id") String id, @Name("data") Map<String, Object> data, IServiceContext context) {
        NopDynPage page = get(id, false, context);
        page.getPageContentComponent().set_jsonValue(data);
    }
}
