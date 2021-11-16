package com.yan.demo.common.enums;

import com.alibaba.fastjson.JSON;
import com.yan.demo.wesockter.Message;
import com.yan.demo.wesockter.message.OnOpenMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：yanfuming
 * @description：code对应的实体类
 * @date ：2021/11/15 3:59 下午
 */
@AllArgsConstructor
@Getter
public enum BodyClassEnum {
    LOGIN(OnOpenMessage.class,1),
    UNLOGIN(OnOpenMessage.class,2),
    ;
    public Class  className;
    public Integer code;


    /**
     *根据code转化为相应body类
     */
    public static Class getBodyClass(int code){
        for (BodyClassEnum value : BodyClassEnum.values()) {
            if(value.getCode()==code){
                return value.getClassName();
            }
        }
        return null;
    }
    /**
     *根据object Body，，获取对应的
     */
    public static Message getMessage(Message message){
        message.setBody(JSON.parseObject(message.getBody().toString(),BodyClassEnum.getBodyClass(message.getCode())));
        return message;
    }


}
