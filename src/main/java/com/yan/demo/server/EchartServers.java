package com.yan.demo.server;

import com.yan.demo.common.enums.ChannelEnum;
import com.yan.demo.common.enums.CountValueEnum;
import com.yan.demo.dao.excelDao.EchartsDao;
import com.yan.demo.dao.excelDao.pojo.Disease;
import com.yan.demo.wesockter.ReturnMessage;
import com.yan.demo.wesockter.returnMessage.Echars1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/16 2:42 下午
 */
@Service
public class EchartServers {
    AtomicLong aLong;

    @Autowired
    EchartsDao echartsDao;

    /**
     *返回原始数据
     */
    public ReturnMessage<List<Disease>> getData(){
        List<Disease> staticData = echartsDao.getStaticData();
        ReturnMessage<List<Disease>> returnMessage = new ReturnMessage<>();
        returnMessage.setBody(staticData);
        returnMessage.setChannel(ChannelEnum.DATA.getValue());
        return returnMessage;
    }

    public ReturnMessage<List> getEcharts1(){
        List<Disease> staticData = echartsDao.getStaticData();
        Map<String, Long> collect = staticData.stream().filter(e->e.getPart()!=null).collect(Collectors.groupingBy(Disease::getPart, Collectors.counting()));

        List<Echars1> arrayList = new ArrayList<>();
        collect.forEach((k,v)->{
            arrayList.add(new Echars1(k, v));
        });
        List<Echars1> rearrayList= arrayList.stream().sorted(Comparator.comparing(Echars1::getCount).reversed()).collect(Collectors.toList()).subList(0,CountValueEnum.ECHARTS1_INTERCEPT.getValue());

        ReturnMessage<List> message = new ReturnMessage<>();
        message.setChannel(ChannelEnum.ECHARTS1.getValue());
        message.setBody(rearrayList);
        return message;
    }
}
