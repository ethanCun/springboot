package com.example.demojpush.entity;

import lombok.Data;

import java.util.Map;

//推送实体类
@Data
public class PushBean {

    //通知内容
    private String alert;

    //附加信息
    private Map<String, String> extras;

    //通知标题 用于安卓 配置这个会代理app名称
    private String title;
}
