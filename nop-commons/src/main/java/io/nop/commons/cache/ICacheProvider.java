/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.nop.commons.cache;

public interface ICacheProvider {
    <K, V> ICache<K, V> getCache(String name);

    void clearAllCache();

    default void clearCache(String name) {
        ICache<?, ?> cache = getCache(name);
        if (cache != null)
            cache.clear();
    }

    default <K, V> void removeCacheItem(String name, K key) {
        ICache<K, V> cache = getCache(name);
        if (cache != null)
            cache.remove(key);
    }
}