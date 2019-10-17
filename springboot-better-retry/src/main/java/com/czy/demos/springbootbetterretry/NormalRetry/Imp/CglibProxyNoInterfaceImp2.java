package com.czy.demos.springbootbetterretry.NormalRetry.Imp;

import org.springframework.stereotype.Service;

@Service("cglibProxyNoInterfaceImp2")
public class CglibProxyNoInterfaceImp2 {

    public String listening(String sth){

        System.out.println("listening sth2: " + sth);

        return "listening sth2: " + sth;
    }
}
