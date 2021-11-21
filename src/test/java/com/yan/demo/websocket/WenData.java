package com.yan.demo.websocket;

import com.alibaba.fastjson.JSON;
import com.yan.demo.wesockter.Message;
import com.yan.demo.wesockter.message.OnOpenMessage;
import okhttp3.*;
import okio.ByteString;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/16 2:20 下午
 */
public class WenData {
    public static void main(String[] args) {
        Client client = new Client() {
            @Override
            public void handle(String val) {
                System.out.println(val);
            }
        };
        System.out.println("222");



        Message<OnOpenMessage> message = new Message<>();
        message.setCode(1);
        message.setBody(OnOpenMessage.builder().channel("echarts4").build());
        System.out.println(JSON.toJSONString(message));
        client.send(JSON.toJSONString(message));
        //message.setBody(OnOpenMessage.builder().channel("echarts1").build());
        client.send(JSON.toJSONString(message));
        message.setBody(OnOpenMessage.builder().channel("echarts3").build());
        client.send(JSON.toJSONString(message));



    }
}
