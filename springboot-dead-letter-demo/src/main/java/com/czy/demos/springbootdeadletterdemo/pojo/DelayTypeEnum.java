package com.czy.demos.springbootdeadletterdemo.pojo;

public enum DelayTypeEnum {

    DELAY_5S,
    DELAY_10S,
    DELAY_20S;

    public int seconds;

    DelayTypeEnum(){}

    DelayTypeEnum(int seconds){

        this.seconds = seconds;
    }
}
