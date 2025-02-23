/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.nop.xlang.xt.model;

import io.nop.xlang.xt.model._gen._XtRuleGroupModel;

public abstract class XtRuleGroupModel extends _XtRuleGroupModel {
    public XtRuleGroupModel() {

    }

    @Override
    public String getXtType() {
        return "xt:group";
    }
}
