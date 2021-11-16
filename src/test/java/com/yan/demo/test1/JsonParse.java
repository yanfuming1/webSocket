package com.yan.demo.test1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yan.demo.common.enums.BodyClassEnum;
import com.yan.demo.wesockter.Message;
import com.yan.demo.wesockter.message.OnOpenMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 4:32 下午
 */
@SpringBootTest
public class JsonParse {


    public static void main(String[] args) {
        Message<OnOpenMessage> message=new Message<>();
        message.setId("1");
        message.setCode(1);
        message.setBody(OnOpenMessage.builder().channel("echart1").build());
        String json= JSON.toJSONString(message);
        System.out.println(json);

        Message message1 = JSON.parseObject(json, Message.class);
        System.out.println(message1.getBody().getClass());
        JSONObject body = (JSONObject) message1.getBody();


        Message message2 = BodyClassEnum.getMessage(message1);

        System.out.println(message2.getBody().getClass());


    }



    @Test
    public void jsontest1(){



    }
}
