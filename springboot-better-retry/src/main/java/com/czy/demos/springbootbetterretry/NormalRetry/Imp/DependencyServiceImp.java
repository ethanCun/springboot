package com.czy.demos.springbootbetterretry.NormalRetry.Imp;

import com.czy.demos.springbootbetterretry.NormalRetry.DependencyService;
import org.springframework.stereotype.Service;

@Service("dependencyService")
public class DependencyServiceImp implements DependencyService {

    @Override
    public String eat(String sth) {

        System.out.println("eatSth:" + sth);

        return sth;
    }
}
