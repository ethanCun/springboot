package com.example.httpclient.demohttpclient.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {

    String name;
    String age;

    User user;
}
