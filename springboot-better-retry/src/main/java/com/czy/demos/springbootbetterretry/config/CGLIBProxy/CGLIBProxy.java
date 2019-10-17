package com.czy.demos.springbootbetterretry.config.CGLIBProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Method;

/**
 * cglib参考学习：https://www.cnblogs.com/writeLessDoMore/p/6973853.html
 *
 *
 *
 * JDK中的动态代理是通过反射类Proxy以及InvocationHandler回调接口实现的
 * 但是，JDK中所要进行动态代理的类必须要实现一个接口，也就是说只能对该类所实现接口中定义的方法进行代理
 * ，这在实际编程中具有一定的局限性，而且使用反射的效率也并不是很高。
 *
 * CGLib：代理增强
 * 使用CGLib实现动态代理，完全不受代理类必须实现接口的限制，而且CGLib底层采用ASM字节码生成框架，
 * 使用字节码技术生成代理类，比使用Java反射效率要高。
 * 唯一需要注意的是，CGLib不能对声明为final的方法进行代理，因为CGLib原理是动态生成被代理类的子类。
 */
@Component
public class CGLIBProxy implements MethodInterceptor {

    /**
     * 调用代理类实例上的proxy方法的父类方法
     *
     * @param o:Object为由CGLib动态生成的代理类实例
     * @param method:Method为上文中实体类所调用的被代理的方法引用
     * @param objects:Object[]为参数值列表
     * @param methodProxy:MethodProxy为生成的代理类对方法的代理引用
     * @return 从代理实例的方法调用返回的值
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable{

        int times = 0;

        while (times < 10){

            //通过代理子类调用父类方法
            try {

                System.out.println("cglib res = " + methodProxy.invokeSuper(o, objects));

                return methodProxy.invokeSuper(o, objects);

            }catch (Exception e){

                times++;

                if (times >= 10){

                    throw new RuntimeException(e);
                }

                e.printStackTrace();
            }
        }

        return null;
    }


    /**
     *通过字节码技术创建目标对象类的子类实例作为代理
     * @param clazz 目标对象类
     * @return 代理子类
     */
    public Object getProxy(Class clazz){

        //Enhancer类是CGLib中的一个字节码增强器，它可以方便的对你想要处理的类进行扩展
        Enhancer enhancer = new Enhancer();

        //目标对象类
        enhancer.setSuperclass(clazz);

        //callback表示回调： CGLIBProxy的intercept方法
        enhancer.setCallback(this);

        //通过字节码技术创建目标对象类的子类实例作为代理
        return enhancer.create();
    }
}
