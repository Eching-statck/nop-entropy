/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.nop.rule.core.service;

import io.nop.rule.api.beans.RuleLogMessageBean;
import io.nop.rule.core.IRuleRuntime;

import java.util.List;

public interface IRuleLogMessageSaver {
    void saveLogMessages(List<RuleLogMessageBean> messages, IRuleRuntime ruleRt);
}
