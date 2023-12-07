package org.ixkit.land.lang.runtime;

import java.io.Serializable;

/**
 * getter方法接口定义
 */
@FunctionalInterface
public interface IGetter<T> extends Serializable {
    Object apply(T source);
}

