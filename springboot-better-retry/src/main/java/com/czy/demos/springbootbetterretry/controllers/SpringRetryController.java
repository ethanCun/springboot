package com.czy.demos.springbootbetterretry.controllers;

import com.czy.demos.springbootbetterretry.Exception.CzyException;
import com.czy.demos.springbootbetterretry.config.SpringRetry.SpringRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringRetryController {

    @Autowired
    private SpringRetryService springRetryService;

    @RequestMapping(value = "/springretry")
    public String springRetry(){

        this.springRetryService.call();

        return "springRetry";
    }

    /**
     * 触发重试的条件：
     * 1. enableretry打开
     * 2. 抛出throwable
     *
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/springretrymanual")
    public String springretrymanual() throws Throwable {

        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(5);

        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        String result = retryTemplate.execute(

                new RetryCallback<String, Throwable>() {

                    @Override
                    public String doWithRetry(RetryContext context) throws Throwable {

                        System.out.println(">>>>>>");

                        throw new CzyException("发生了异常...");
                    }
                }
                ,
                new RecoveryCallback<String>() {

                    @Override
                    public String recover(RetryContext context) throws Exception {

                        return "recover...";
                    }
                }
        );

        return result;
    }
}
