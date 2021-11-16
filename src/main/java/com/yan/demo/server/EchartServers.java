package com.yan.demo.server;

import com.yan.demo.common.enums.ChannelEnum;
import com.yan.demo.dao.excelDao.EchartsDao;
import com.yan.demo.dao.excelDao.pojo.Disease;
import com.yan.demo.wesockter.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

}
