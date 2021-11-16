package com.yan.demo.wesockter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 2:54 下午
 */
@Slf4j
@Component
public class WebSocketStart {
    @Autowired
    WebSocketClient webSocketClient;

    //@PostConstruct
    @SneakyThrows
    public void start(){
        webSocketClient.channels.put("echarts1",new CopyOnWriteArraySet<>());
        log.info("websocket初始化");

    }
}
