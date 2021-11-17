package com.yan.demo.websocket;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;

import java.util.concurrent.TimeUnit;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/17 5:41 下午
 */
@Slf4j
public abstract class Client {

    static WebSocket websocket;
    static boolean onOpen=false;

    public Client() {
        onWebSocket();
    }

    public void onWebSocket(){
        OkHttpClient mClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(3, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        //连接地址
        String url = "ws://182.92.72.202:9000/webSocket";
        //构建一个连接请求对象
        Request request = new Request.Builder().get().url(url).build();

        //开始连接
        WebSocket web = null;
        while (!onOpen||web == null) {
            web = mClient.newWebSocket(request, new WebSocketListener() {
                @Override
                public void onOpen(WebSocket webSocket, Response response) {
                    super.onOpen(webSocket, response);
                    log.info("链接成功");
                    onOpen=true;
                    //连接成功...
                }

                @Override
                public void onMessage(WebSocket webSocket, String text) {
                    handle(text);
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
                    log.error("连接失败--重试");
                }

            });
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        websocket=web;
    }

    public void send(String val){
        websocket.send(val);
    }

    public abstract void  handle(String val);


}
