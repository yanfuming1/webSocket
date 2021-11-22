package com.yan.demo.test1;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/22 2:59 下午
 */
public class T1 implements Serializable {
    public static void main(String[] args) {

        DateTime dateTime= DateTime.now();
        int week = dateTime.getMonthOfYear();
        System.out.println(week);

    }
}

@Data
class User implements Serializable {
    Integer id;
    String name;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}