package com.yan.demo.servers;

import com.yan.demo.server.EchartServers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/16 2:56 下午
 */
@SpringBootTest
public class EchartsServerTest {
    @Autowired
    EchartServers echartServers;
    
    @Test
    public void t1(){
       // echartServers.echarts1();
    }
}
