package com.yan.demo.common.enums;

import com.yan.demo.common.Enum2ArgGeneral;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/17 5:04 下午
 */

@Getter
@AllArgsConstructor
public enum CountValueEnum implements Enum2ArgGeneral<Integer,String> {
    ECHARTS1_INTERCEPT(5,"截取数量"),
    ;
    Integer value;
    String Content;
}
