package com.yan.demo.dao.excelDao;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.yan.demo.dao.excelDao.pojo.Disease;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/16 2:44 下午
 */
@Repository
@Slf4j
public class EchartsDao {
    static String fileName="disease.csv";

    static List<Disease> diseaseList = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void getEchartsData(){
        Long start=System.currentTimeMillis();
        ArrayList<Disease> diseases = new ArrayList<>();
        String fileName ="/Users/guanliyuan/Desktop/demo/src/main/java/com/yan/demo/dao/excelDao/disease.csv";
        EasyExcel.read(fileName, Disease.class, new PageReadListener<Disease>(dataList -> {
            diseases.addAll(dataList);
        })).sheet().doRead();
        diseaseList.clear();
        diseaseList=diseases;
        log.error("读取CSV-----》耗时{}",System.currentTimeMillis()-start);
    }

    public List<Disease> getStaticData(){
        return diseaseList;
    }
}
