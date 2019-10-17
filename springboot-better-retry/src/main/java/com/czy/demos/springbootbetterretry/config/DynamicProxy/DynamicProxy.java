package com.czy.demos.springbootbetterretry.config.DynamicProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalTime;

//@Component
@Slf4j
public class DynamicProxy implements InvocationHandler {

    private final Object subject;

    //构造函数，给我们的真实对象赋值
    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        int times = 0;

        while (times < 10){

            try {

                //obj: subject真实对象调用返回的值
                //subject:真实的实现接口的类
                //args:传递的参数
                Object obj = method.invoke(subject, args);

                System.out.println("obj = " + obj + " subject = " + subject + " method = " + method.getName());

                for (int i = 0; i < args.length; i++) {

                    System.out.println("args = " + args[i]);
                }

                return obj;

            }catch (Exception e){

                times++;

                log.info("times:{}, time:{}", times, LocalTime.now());

                if (times >= 4){

                    throw new RuntimeException(e);
                }
            }
        }

        //延时一秒
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return null;
    }

    //获取动态代理
    //参数：动态代理对象
    public static Object getProxy(Object realSubject){

        InvocationHandler handler = new DynamicProxy(realSubject);

        //创建一个代理类对象
        //loader：一个classloader对象，定义了由哪个classloader对象对生成的代理类进行加载
        //interfaces：一个interface对象数组，表示我们将要给我们的代理对象提供一组什么样的接口，
        //如果我们提供了这样一个接口对象数组，那么也就是声明了代理类实现了这些接口，代理类就可以调用接口中声明的所有方法。
        //一个InvocationHandler对象，表示的是当动态代理对象调用方法的时候会关联到哪一个InvocationHandler对象上，
        //并最终由其调用。
        return Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);
    }
}
