package com.example.demojpush.service.imp;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.example.demojpush.config.JPushConfig;
import com.example.demojpush.entity.PushBean;
import com.example.demojpush.service.MyJPushService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyJPushServiceImp implements MyJPushService {

    @Autowired
    private JPushConfig jPushConfig;

    @Override
    public boolean pushAll(PushBean pushBean) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.all()).setAudience(Audience.all())
                .setNotification(Notification.alert(pushBean.getAlert()))
                .build());
    }

    @Override
    public boolean pushIos(PushBean pushBean) {
        return sendPush(PushPayload.newBuilder()
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.ios(pushBean.getAlert(), pushBean.getExtras()))

                .build());
    }

    @Override
    public boolean pushIos(PushBean pushBean, String... regIds) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.registrationId(regIds))
                .setNotification(Notification.ios(pushBean.getAlert(), pushBean.getExtras()))
                .build());
    }

    @Override
    public boolean pushAndriod(PushBean pushBean) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(pushBean.getAlert(),pushBean.getTitle(),pushBean.getExtras()))
                .build());
    }

    @Override
    public boolean pushAndriod(PushBean pushBean, String... regIds) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(regIds))
                .setNotification(Notification.android(pushBean.getAlert(),pushBean.getTitle(),pushBean.getExtras()))
                .build());
    }

    @Override
    public boolean customContentIos(String content) {
        return sendPush(PushPayload.newBuilder().setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(content).build())
                .build());
    }

    @Override
    public boolean customContentAndriod(String content) {
        return sendPush(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(content).build())
                .build());
    }

    @Override
    public boolean sendPush(PushPayload pushPayload) {

        System.out.println("发送极光推送请求: " + pushPayload);

        PushResult pushResult = null;

        try {

            pushResult = jPushConfig.getjPushClient().sendPush(pushPayload);

        }catch (APIConnectionException e){
            System.out.println("极光连接异常: " + e.getMessage());
        }catch (APIRequestException e){
            System.out.println("极光请求异常: " + e.getMessage());
        }

        if (pushResult != null && pushResult.isResultOK()){
            System.out.println("极光推送请求成功: " + pushResult);
            return true;
        }else {
            System.out.println("极光推送请求失败: " + pushResult);
            return false;
        }
    }


}
