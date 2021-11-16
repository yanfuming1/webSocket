package com.yan.demo.common;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 3:21 下午
 */
public interface Enum2ArgGeneral<V,C> {
    V getValue();

    C getContent();

    default boolean equalsWithValue(V value) {
        return this.getValue().equals(value);
    }
}
