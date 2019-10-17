package com.czy.demos.springbootbetterretry.NormalRetry.Imp;

import com.czy.demos.springbootbetterretry.NormalRetry.CustomAnnotationRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service("customAnnotationRetry")
public class CustomAnnotationRetryImp implements CustomAnnotationRetry {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public void retryByCustomAnnotation() {

        long times = atomicLong.getAndIncrement();

        if (times%5 == 0){

            System.out.println("调用成功");
        }else {

            System.out.println("调用失败");
        }
    }
}
