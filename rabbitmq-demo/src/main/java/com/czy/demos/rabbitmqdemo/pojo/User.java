package com.czy.demos.rabbitmqdemo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {


    private static final long serialVersionUID = -4843271630100736769L;

    private String username;
    private String password;
}
