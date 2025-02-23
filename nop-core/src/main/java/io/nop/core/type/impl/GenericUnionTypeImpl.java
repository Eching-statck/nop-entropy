/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.nop.core.type.impl;

import io.nop.commons.util.StringHelper;
import io.nop.core.type.GenericTypeKind;
import io.nop.core.type.IGenericType;
import io.nop.core.type.IUnionType;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GenericUnionTypeImpl extends AbstractCompositeType implements IUnionType {
    private String typeName;

    public GenericUnionTypeImpl(List<IGenericType> subTypes) {
        super(subTypes);
    }

    @Override
    public String getRawTypeName() {
        Set<String> types = new LinkedHashSet<>();
        for (IGenericType subType : getSubTypes()) {
            types.add(subType.getRawTypeName());
        }
        return StringHelper.join(types, "|");
    }

    public String getTypeName() {
        if (typeName == null)
            typeName = StringHelper.join(getSubTypes(), "|");
        return typeName;
    }

    @Override
    public GenericTypeKind getKind() {
        return GenericTypeKind.UNION;
    }
}