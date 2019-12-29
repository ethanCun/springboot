package com.example.demojpush.controller;

import com.example.demojpush.config.JPushConfig;
import com.example.demojpush.entity.PushBean;
import com.example.demojpush.service.MyJPushService;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class JPushController {

    @Autowired
    private MyJPushService myJPushService;

    @ResponseBody
    @GetMapping(value = "/pushAndriod")
    public boolean pushAndiod(String title, String content){

        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setAlert(content);
        boolean res = myJPushService.pushAndriod(pushBean);

        return res;
    }

    @ResponseBody
    @GetMapping(value = "/pushIos")
    public void pushIos(String alert, @RequestParam(value = "extras") Map<String, String> extras){

        System.out.println("alert = " + alert + " extras = " + extras);

        PushBean pushBean = new PushBean();
        pushBean.setExtras(extras);
        pushBean.setAlert(alert);

        boolean res = myJPushService.pushIos(pushBean);

        System.out.println("推送结果: " + res);
    }

    @ResponseBody
    @GetMapping(value = "/pushIosWithRegIds")
    public void pushIos(String alert, @RequestParam(value = "extras") Map<String, String> extras, String... regIds){

        System.out.println("alert = " + alert + " extras = " + extras + " regIds = " + regIds);

        PushBean pushBean = new PushBean();
        pushBean.setExtras(pushBean.getExtras());
        pushBean.setAlert(pushBean.getAlert());

        boolean res = myJPushService.pushIos(pushBean, regIds);

        System.out.println("推送结果: " + res);
    }

    @ResponseBody
    @GetMapping(value = "/customContent")
    public void customContent(String content){

        System.out.println("content = " + content);
        boolean res = myJPushService.customContentIos(content);

        System.out.println("推送结果: " + res);
    }
}
