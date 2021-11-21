package com.yan.demo.server;

import com.yan.demo.common.enums.ChannelEnum;
import com.yan.demo.common.enums.CountValueEnum;
import com.yan.demo.dao.excelDao.EchartsDao;
import com.yan.demo.dao.excelDao.pojo.Disease;
import com.yan.demo.wesockter.ReturnMessage;
import com.yan.demo.wesockter.returnMessage.Echars1;

import com.yan.demo.wesockter.returnMessage.EcharsBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    static AtomicLong aLong=new AtomicLong(0L);

    @Autowired
    EchartsDao echartsDao;

    /**
     *返回原始数据
     */
    public ReturnMessage<List<Disease>> getData(){
        List<Disease> staticData = echartsDao.setStaticData();
        ReturnMessage<List<Disease>> returnMessage = new ReturnMessage<>();
        returnMessage.setBody(staticData);
        returnMessage.setChannel(ChannelEnum.DATA.getValue());
        return returnMessage;
    }

    /**
     *获取病例发生部位统计
     */
    public ReturnMessage<List<Echars1>> getEcharts1(){
        List<Disease> staticData = echartsDao.getStaticData();
        Map<String, Long> collect = staticData.stream().filter(e->e.getPart()!=null).collect(Collectors.groupingBy(Disease::getPart, Collectors.counting()));

        List<Echars1> arrayList = new ArrayList<>();
        collect.forEach((k,v)->{
            arrayList.add(new Echars1(k, v+aLong.get()));
        });
        List<Echars1> rearrayList= arrayList.stream().sorted(Comparator.comparing(Echars1::getValue).reversed()).collect(Collectors.toList()).subList(0,CountValueEnum.ECHARTS1_INTERCEPT.getValue());

        ReturnMessage<List<Echars1>> message  = new ReturnMessage<>();
        message.setChannel(ChannelEnum.ECHARTS1.getValue());
        message.setBody(rearrayList);

        Random random = new Random();
        int i = random.nextInt(1000);
        aLong.addAndGet(i-500) ;
        return message;
    }

    /**
     *传染性统计
     */
    public ReturnMessage<List<Echars1>> getEcharts2(){
        List<Disease> staticData = echartsDao.getStaticData();
        Map<String, Long> collect = staticData.stream().filter(e -> e.getInfection() != null).collect(Collectors.groupingBy(Disease::getInfection, Collectors.counting()));

        List<Echars1> arrayList = new ArrayList<>();
        collect.forEach((k,v)->{
            arrayList.add(new Echars1(k, v+aLong.get()));
        });
        List<Echars1> rearrayList= arrayList.stream().sorted(Comparator.comparing(Echars1::getValue).reversed()).collect(Collectors.toList());
        ReturnMessage<List<Echars1>> message  = new ReturnMessage<>();
        message.setChannel(ChannelEnum.ECHARTS2.getValue());
        message.setBody(rearrayList.subList(0,CountValueEnum.ECHARTS2_INTERCEPT.getValue()));

        return message;
    }


    /**
     *所挂科室
     */
    public ReturnMessage<List<Echars1>> getEcharts3(){
        List<Disease> staticData = echartsDao.getStaticData();
        List<String> datas=new ArrayList<>();

        staticData.forEach(data->{
            if(StringUtils.isEmpty(data.getDepartment())) return;
            String[] strings = data.getDepartment().split("\\s+");
            datas.addAll(Arrays.asList(strings));
        });
        Map<String, Long> collect = datas.stream().collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        List<Echars1> arrayList = new ArrayList<>();
        collect.forEach((k,v)->{
            arrayList.add(new Echars1(k, v+aLong.get()));
        });
        arrayList.sort((a,b)-> -a.getValue().compareTo(b.getValue()));
        return value(arrayList,ChannelEnum.ECHARTS3,CountValueEnum.ECHARTS3_INTERCEPT);
    }

    /**
     *是否医保
     */
    public ReturnMessage<List<Echars1>> getEcharts4(){
        List<Disease> staticData = echartsDao.getStaticData();
        List<Echars1> list = groupCount(staticData.stream().map(Disease::getInsurance).collect(Collectors.toList()));
       return   value(list,ChannelEnum.ECHARTS4,CountValueEnum.ECHARTS4_INTERCEPT);
    }

    /**
     *病症名称
     */
    public ReturnMessage<List<Echars1>> getEcharts5(){
        List<Disease> staticData = echartsDao.getStaticData();
        List<Echars1> list = groupCount(staticData.stream().map(Disease::getName).collect(Collectors.toList()));
        return   value(list,ChannelEnum.ECHARTS5,CountValueEnum.ECHARTS5_INTERCEPT);
    }



    public <T> ReturnMessage<List<T>> value(List<T> arrayList, ChannelEnum channelEnum, CountValueEnum countValueEnum){
       return value(arrayList, channelEnum,0, countValueEnum);
    }

    public <T> ReturnMessage<List<T>> value(List<T> arrayList,ChannelEnum channelEnum,int start,CountValueEnum countValueEnum){
        ReturnMessage<List<T>> message  = new ReturnMessage<>();
        message.setChannel(channelEnum.getValue());
        message.setBody(arrayList.subList(start, countValueEnum.getValue()));
        return message;
    }


    public  List<Echars1> groupCount(List<String> data){
        Map<String, Long> collect = data.stream().filter(s -> !StringUtils.isEmpty(s)).collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        List<Echars1> arrayList = new ArrayList<>();
        collect.forEach((k,v)->{
            arrayList.add(new Echars1(k, v+aLong.get()));
        });

        List<Echars1> reArrayList= arrayList.stream().sorted(Comparator.comparing(Echars1::getValue).reversed()).collect(Collectors.toList());
        return reArrayList;
    }
}
