package com.example.demojpush.service;

import cn.jpush.api.push.model.PushPayload;
import com.example.demojpush.entity.PushBean;


/**
 * jpush 封装第三方api
 */
public interface MyJPushService {

    //广播
    boolean pushAll(PushBean pushBean);

    //推送
    boolean pushIos(PushBean pushBean);
    boolean pushIos(PushBean pushBean, String... regIds);
    //自定义消息
    boolean customContentIos(String content);

    boolean pushAndriod(PushBean pushBean);
    boolean pushAndriod(PushBean pushBean, String... regIds);
    //自定义消息
    boolean customContentAndriod(String content);


    boolean sendPush(PushPayload pushPayload);
}
