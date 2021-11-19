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
    ECHARTS1_INTERCEPT(5,"echarts1截取数量"),
    ECHARTS2_INTERCEPT(5,"echarts2截取数量"),
    ECHARTS3_INTERCEPT(5,"echarts3截取数量"),
    ECHARTS4_INTERCEPT(2,"echarts4截取数量"),
    ECHARTS5_INTERCEPT(5,"echarts5截取数量"),
    ECHARTS6_INTERCEPT(5,"echarts6截取数量"),
    ECHARTS7_INTERCEPT(5,"echarts7截取数量"),
    ;
    Integer value;
    String Content;
}
