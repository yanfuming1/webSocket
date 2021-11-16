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
        OkHttpClient mClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(3, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        //连接地址
        String url = "ws://127.0.0.1:8080/webSocket";
        //构建一个连接请求对象
        Request request = new Request.Builder().get().url(url).build();

        //开始连接
        WebSocket websocket = mClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                System.out.println("123");
                //连接成功...
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                System.out.println(text);
                super.onMessage(webSocket, text);
                //收到消息...（一般是这里处理json）
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
                //收到消息...（一般很少这种消息）
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                //连接关闭...
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable throwable, Response response) {
                super.onFailure(webSocket, throwable, response);
                //连接失败...
                System.out.println("123--");

            }

        });
        System.out.println("222");



        Message<OnOpenMessage> message = new Message<>();
        message.setCode(1);
//        message.setBody(OnOpenMessage.builder().channel("echarts1").build());
//        websocket.send(JSON.toJSONString(message));
        message.setBody(OnOpenMessage.builder().channel("data").build());
        websocket.send(JSON.toJSONString(message));


    }
}
