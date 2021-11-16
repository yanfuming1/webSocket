package com.yan.demo.common.enums;

import com.yan.demo.common.Enum2ArgGeneral;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 3:35 下午
 */
public interface ChannelsEnum {
    @Getter
    @AllArgsConstructor
    enum TfChannelEnum implements Enum2ArgGeneral<Integer,String> {
        SUBSCRIBE(1, "订阅"),
        UNSUBSCRIBE(2, "取消订阅"),
        END_MESSAGE(3,"接收消息")
        ;
        final Integer value;
        final  String Content;

    }
}
