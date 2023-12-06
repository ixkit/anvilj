package org.ixkit.land.lang.runtime;

import java.io.Serializable;

/**
 * setter方法接口定义
 */
@FunctionalInterface
public interface ISetter<T, U> extends Serializable {
    void accept(T t, U u);
}