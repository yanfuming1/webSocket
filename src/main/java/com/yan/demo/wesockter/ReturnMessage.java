package com.yan.demo.wesockter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 2:23 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnMessage <T>{

    /**
     *通道
     */
    String channel;

    T body;


}
