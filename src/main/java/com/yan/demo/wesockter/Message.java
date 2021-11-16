package com.yan.demo.wesockter;

import com.yan.demo.common.enums.ChannelsEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 2:00 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message<T> {
    /**
     *code值
     * @see ChannelsEnum.TfChannelEnum
     */
    Integer code;

    /**
     *用户id
     */
    String id;



    T body;



}
