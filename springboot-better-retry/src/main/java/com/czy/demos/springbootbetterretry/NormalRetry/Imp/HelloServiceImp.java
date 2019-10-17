package com.czy.demos.springbootbetterretry.NormalRetry.Imp;

import com.czy.demos.springbootbetterretry.Exception.CzyException;
import com.czy.demos.springbootbetterretry.NormalRetry.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service("helloService")
public class HelloServiceImp implements HelloService {

    private static AtomicLong atomicLong = new AtomicLong();

    //测试动态代理依赖其他的类时会不会报错
    //结论：如果被代理的类依赖了其它被Spring容器管理的类，则这种方式会抛出异常，
    //因为没有把被依赖的实例注入到创建的代理实例中。
//    @Autowired
//    private DependencyServiceImp dependencyServiceImp;

    //每次动态创建实例可以解决这个问题
//    public DependencyServiceImp dependencyServiceImp(){
//
//        return new DependencyServiceImp();
//    }

    @Override
    public String saySth(String something) {

        System.out.println("saySth:" + something);

//        dependencyServiceImp.eat(something);
        //dependencyServiceImp().eat(something);

        long times = atomicLong.incrementAndGet();

        System.out.println("times = " + times);

        //没四次访问调用一次rpc成功
        if (times % 9 != 0){

            throw new CzyException("rpc调用失败");
        }

        System.out.println("rpc调用成功");

        return something;
    }
}
