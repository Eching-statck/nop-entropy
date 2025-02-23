/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.nop.rpc.cluster;

import io.nop.cluster.discovery.ServiceInstance;
import io.nop.rpc.api.IRpcService;

/**
 * 管理所有到具体服务器的连接
 */
public interface IRpcClientInstanceProvider {
    IRpcService getRpcClientInstance(ServiceInstance instance);
}
