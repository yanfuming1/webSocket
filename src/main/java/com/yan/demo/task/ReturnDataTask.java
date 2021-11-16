package com.yan.demo.task;

import com.yan.demo.common.enums.ChannelEnum;
import com.yan.demo.dao.excelDao.pojo.Disease;
import com.yan.demo.server.EchartServers;
import com.yan.demo.wesockter.ReturnMessage;
import com.yan.demo.wesockter.WebSocketClient;
import com.yan.demo.wesockter.returnMessage.Echars1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/16 5:07 下午
 */
@Configuration
@EnableScheduling
@Slf4j
public class ReturnDataTask {
    @Autowired
    WebSocketClient webSocketClient;
    @Autowired
    EchartServers echartServers;

    @Scheduled(cron = "0/1 * * * * ?")
    public void sendata() {

        webSocketClient.sendMessage(echartServers.getData());
    }




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
