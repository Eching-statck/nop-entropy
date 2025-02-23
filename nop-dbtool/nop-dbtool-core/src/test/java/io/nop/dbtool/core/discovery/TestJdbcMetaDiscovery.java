/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.nop.dbtool.core.discovery;

import io.nop.autotest.junit.JunitBaseTestCase;
import io.nop.dbtool.core.DataBaseMeta;
import io.nop.dbtool.core.discovery.jdbc.JdbcMetaDiscovery;
import io.nop.orm.OrmConstants;
import io.nop.orm.model.OrmModel;
import io.nop.xlang.xdsl.DslModelHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.util.List;

@Disabled
public class TestJdbcMetaDiscovery extends JunitBaseTestCase {

    @Inject
    DataSource dataSource;

    @Test
    public void testDiscovery() {
        JdbcMetaDiscovery discovery = new JdbcMetaDiscovery();
        List<String> catalogs = discovery.getCatalogs(dataSource);
        System.out.println("catalogs="+catalogs);

        DataBaseMeta meta = discovery.discover(dataSource, "datart", null, "%");

        OrmModel model = meta.getOrmModel();
        DslModelHelper.dslModelToXNode(OrmConstants.XDSL_SCHEMA_ORM, model).dump();
    }
}
