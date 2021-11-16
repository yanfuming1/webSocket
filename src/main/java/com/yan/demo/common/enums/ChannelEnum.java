package com.yan.demo.common.enums;

import com.yan.demo.common.Enum2ArgGeneral;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 2:44 下午
 */
@AllArgsConstructor
public enum ChannelEnum implements Enum2ArgGeneral<String,String > {
    ECHARTS1("echarts1","第一个图"),
    DATA("data","原始数据")
    ;
    final String value;
    final String Content;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getContent() {
        return Content;
    }
}
