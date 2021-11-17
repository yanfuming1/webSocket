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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/16 2:44 下午
 */
@Repository
@Slf4j
public class EchartsDao {

    static ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();


    static String fileName;

    static List<Disease> diseaseList = new CopyOnWriteArrayList<>();

    public EchartsDao() throws IOException {
        File directory = new File("");//参数为空
        String courseFile = directory.getCanonicalPath();//标准的路径 ;
        String author = directory.getAbsolutePath();//绝对路径;
        fileName = author + "/src/main/resources/disease.csv";
    }

    @PostConstruct
    public void getEchartsData() {


        Long start = System.currentTimeMillis();
        ArrayList<Disease> diseases = new ArrayList<>();
//        String fileName ="/Users/guanliyuan/Desktop/demo/src/main/java/com/yan/demo/dao/excelDao/disease.csv";
        EasyExcel.read(fileName, Disease.class, new PageReadListener<Disease>(dataList -> {
            diseases.addAll(dataList);
        })).sheet().doRead();


        /**
         *使用并发集合  不加锁
         */
        //  writeLock.lock();
        diseaseList.clear();
        diseaseList = diseases;
        //   writeLock.unlock();
        log.error("读取CSV-----》耗时{}", System.currentTimeMillis() - start);
    }

    public List<Disease> getStaticData() {
        //    readLock.lock();
        List<Disease> data = diseaseList;
        //   readLock.unlock();
        return data;
    }

    public List<Disease> setStaticData() {
        getEchartsData();
        return getStaticData();
    }
}
