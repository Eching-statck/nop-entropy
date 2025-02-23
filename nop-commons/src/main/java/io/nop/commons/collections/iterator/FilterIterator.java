/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package io.nop.commons.collections.iterator;

import io.nop.api.core.util.Guard;
import io.nop.commons.util.IoHelper;

import java.util.Iterator;
import java.util.function.Predicate;

public class FilterIterator<E> implements IPeekingIterator<E>, AutoCloseable {
    private static final Object NULL = new Object();

    private final Iterator<? extends E> iterator;
    private final Predicate<? super E> filter;
    private Object next;

    public FilterIterator(Iterator<? extends E> iterator, Predicate<? super E> filter) {
        this.iterator = Guard.notNull(iterator, "iterator is null");
        this.filter = filter;
        this.next = advance();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E next() {
        if (next != null)
            return next == NULL ? null : (E) next;

        next = advance();
        return next == NULL ? null : (E) next;
    }

    private Object advance() {
        if (iterator.hasNext()) {
            do {
                E o = iterator.next();
                if (filter.test(o)) {
                    return o == null ? NULL : o;
                }
            } while (iterator.hasNext());
        }
        return NULL;
    }

    @Override
    public void remove() {
        iterator.remove();
    }

    @Override
    public void close() throws Exception {
        IoHelper.safeClose(iterator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        return next == NULL ? null : (E) next;
    }
}