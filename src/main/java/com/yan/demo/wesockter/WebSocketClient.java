package com.yan.demo.wesockter;

import com.alibaba.fastjson.JSON;
import com.yan.demo.common.enums.BodyClassEnum;
import com.yan.demo.common.enums.ChannelsEnum;
import com.yan.demo.common.EnumHelp;
import com.yan.demo.wesockter.message.OnOpenMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 1:46 下午
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocketClient {
    Session session;
    String name;

    static CopyOnWriteArraySet<Session> userSet=new CopyOnWriteArraySet<>();
    static ConcurrentHashMap<String,CopyOnWriteArraySet<Session>> channels=new ConcurrentHashMap<>();

    @OnOpen
    public void onOpne(Session session){
        this.session=session;
        userSet.add(session);
        log.info("{}==链接成功",session.toString());
    }
    @OnMessage
    public void onMessage(String message){


        Message messages = JSON.parseObject(message, Message.class);

        //直接获取code值操作
//        JSONObject jsonObject = JSON.parseObject(message);
//        int code=jsonObject.getInteger("code");

        log.info("接收到消息-{}",messages);

        switch (EnumHelp.getByValue(ChannelsEnum.TfChannelEnum.class, messages.getCode())) {
            case SUBSCRIBE:
                subscribeChannel(BodyClassEnum.getMessage(messages));
                break;
            case UNSUBSCRIBE:
                unSubscribeChannel(BodyClassEnum.getMessage(messages));
                break;
            default:
                break;
        }
    }

    @OnClose
    public void onClose(){
        userSet.remove(this.session);
        channels.forEach((k,v)->{
            v.forEach(channel->{
                if(channel==this.session){
                    log.warn("用户断开链接{}",this.session);
                    v.remove(this.session);
                }
            });

            if(v.size()==0){
                log.warn("该通道没有成员-删除{}",k);
                channels.remove(k);
            }
        });
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        log.warn("客户端连接异常，断开连接，sessionId={}，e={}",session.getId(),throwable.getMessage());
    }

    /**
     *推送消息
     */
    public  void sendMessage(ReturnMessage message){
        String channel=message.getChannel();
        log.info("推送{}频道",message.getChannel());
        if(channels.get(channel)==null){
            log.warn("{}频道无人订阅",channel);
            return;
        }
        channels.get(channel).forEach(userSession->{
            try {
                userSession.getBasicRemote().sendText(JSON.toJSONString(message));
            } catch (IOException e) {
                log.error("推送失败");
            }
        });

    }



    public void subscribeChannel(Message<OnOpenMessage> onOpenMessage){
        String channel = onOpenMessage.getBody().getChannel();
        if(!channels.containsKey(channel)){
            CopyOnWriteArraySet<Session> set = new CopyOnWriteArraySet<>();
            set.add(this.session);
            channels.put(channel,set);
        }else {
            channels.get(channel).add(this.session);
        }
    }

    public void unSubscribeChannel(Message<OnOpenMessage> onOpenMessage){

    }


}
