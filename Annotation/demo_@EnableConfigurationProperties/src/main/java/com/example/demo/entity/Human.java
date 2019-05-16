package com.example.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class Human {

    public Human(){

        System.out.println("human");
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
