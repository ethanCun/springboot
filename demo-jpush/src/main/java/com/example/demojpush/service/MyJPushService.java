package com.example.demojpush.service;

import cn.jpush.api.push.model.PushPayload;
import com.example.demojpush.entity.PushBean;


/**
 * jpush 封装第三方api
 */
public interface MyJPushService {

    //广播
    boolean pushAll(PushBean pushBean);

    boolean pushIos(PushBean pushBean);

    boolean pushAndriod(PushBean pushBean);

    boolean pushIos(PushBean pushBean, String... regIds);

    boolean pushAndriod(PushBean pushBean, String... regIds);

    boolean sendPush(PushPayload pushPayload);
}
