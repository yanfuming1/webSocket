package com.yan.demo.task;

import com.yan.demo.common.enums.ChannelEnum;
import com.yan.demo.wesockter.ReturnMessage;
import com.yan.demo.wesockter.WebSocketClient;
import com.yan.demo.wesockter.returnMessage.Echars1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 2:36 下午
 */
@Configuration
@EnableScheduling
@Slf4j
public class ReturnBodyTest {
//    @Autowired
//    WebSocketClient webSocketClient;
//
//    @Scheduled(cron = "0/1 * * * * ?")
//    public void send1(){
//
//        ReturnMessage<Echars1> message = new ReturnMessage<>();
//        message.setChannel(ChannelEnum.ECHARTS1.getValue());
//        message.setBody(Echars1.builder().data("测试").build());
//        webSocketClient.sendMessage(message);
//        log.info("定时任务开启 推动消息{}",message);
//    }

}
