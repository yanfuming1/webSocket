package com.yan.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 3:00 下午
 */
@Component
public class WebSocketConfig {
    /**
     *让远程链接
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
