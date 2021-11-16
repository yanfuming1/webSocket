package com.yan.demo.common;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 3:17 下午
 */
public class EnumHelp {
    public EnumHelp() {
    }

    public static <E extends Enum2ArgGeneral> E getByValue(Class<E> enumClass, Object value) {
        List<E> values = Arrays.asList(enumClass.getEnumConstants());
        Iterator var3 = values.iterator();

        Enum2ArgGeneral e;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            e = (Enum2ArgGeneral)var3.next();
        } while(!e.getValue().equals(value));

        return (E) e;
    }
}
