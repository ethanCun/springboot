package com.example.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 *
 * 要想把一个类变成切面类，需要两步，
 * ① 在类上使用 @Component 注解 把切面类加入到IOC容器中
 * ② 在类上使用 @Aspect 注解 使之成为切面类
 * */
@Component
@Aspect
public class WebLogAcpect {

    private Logger logger = LoggerFactory.getLogger(WebLogAcpect.class);

    /**
     * 定义切入点:
     * execution表达式详解: 切入点表达式的格式：execution([可见性]返回类型[声明类型].方法名(参数)[异常])
     *
     * execution(): 表达式主体。
     * 第一个*号：表示返回类型， *号表示所有的类型。
     * 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.example.demo包、子孙包下所有类的方法。
     * 第二个*号：表示类名，*号表示所有的类。
     * *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     * */
    @Pointcut("execution(public * com.example.demo..*.*(..))")
    public void executionPointCut(){}

    /**
     * 前置通知：方法调用前被调用
     * */
    @Before("executionPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

        //接收到请求，记录请求内容 RequestContextHolder:通过RequestContextHolder来获取请求信息，Session信息；
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录下请求内容
        logger.info("URL: " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD: " + request.getMethod());
        logger.info("IP " + request.getRemoteAddr());
        logger.info("CLASS_INFO: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
        logger.info("this: " + joinPoint.getThis());
        logger.info("tagrget: " + joinPoint.getTarget());
    }


    /**
     *  后置返回通知
     *  这里需要注意的是:
     *  如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *  如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     *  returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
     *  对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * */
    @AfterReturning(value = "executionPointCut()", returning = "keys")
    public void doAfterReturning1(JoinPoint joinPoint, Object keys){

        logger.info("第一个后置通知的返回值: " + keys);
    }


    @AfterReturning(value = "executionPointCut()", returning = "keys", argNames = "keys")
    public void doAfterReturning2(Object keys){

        logger.info("第二个后置通知的返回值: " + keys);
    }

    /**
     * 后置异常通知
     * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     * throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * */
    @AfterThrowing(value = "executionPointCut()", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception){

        //目标方法名
        logger.info(joinPoint.getSignature().getName());
    }

    /**
     * 后置最终通知@After：当某连接点退出时执行的通知（不论是正常返回还是异常退出）。
     * */
    @After(value = "executionPointCut()")
    public void doAfterAdvice(JoinPoint joinPoint){

        logger.info("后置最终通知");
    }

    /**
     * 环绕通知：
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     * */
    @Around(value = "executionPointCut()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){

        logger.info("环绕通知的目标方法名: " + proceedingJoinPoint.getSignature().getName());

        try {

            Object obj = proceedingJoinPoint.proceed();
            logger.info("环绕通知proceed产生的对象: " + obj);
            return obj;

        }catch (Throwable throwable){

            throwable.printStackTrace();
        }

        return null;
    }

    /**
     * 使用args来绑定参数名称
     * */
    @Before(value = "execution(public * com.example.demo..*.*(..))&&" + "args(name,..)")
    public void testGetArgs(String name){

        logger.info("使用args来接受接口中传过来的数据: " + name);
    }


    /**
     *
     * 切入点表达式除了有execution, 还有within, this, target, args
     * within：用于匹配连接点所在的Java类或者包。
     * */
    //匹配helloController2这个类
    @Pointcut("within(com.example.demo.controller.HelloController2)")
    public void withinPointCut1(){}
    //匹配com.example.demo下的所有类 所有方法
    @Pointcut("within(com.example.demo..*)")
    public void withinPointCut2(){}

    //测试within
    @Before(value = "withinPointCut1()")
    public void testWithin1(){

        logger.info("within1.....");
    }
    //测试within
    @Before(value = "withinPointCut2()")
    public void testWithin2(){

        logger.info("within2.....");
    }

    /**
     * @within ：用于匹配在类一级使用了参数确定的注解的类，其所有方法都将被匹配。
     * */
    @Pointcut("@within(com.example.demo.common.CzyAdviceAnnotation)")
    public void testAtWithinPointCut(){}

    //测试@within
    @Before(value = "testAtWithinPointCut()")
    public void testAtWithin(JoinPoint joinPoint){

        logger.info("@within....");
    }

    /**
     * @target ：和@within的功能类似，但必须要指定注解接口的保留策略为RUNTIME。
     * */
//    @Pointcut("@target(com.example.demo.common.CzyAdviceAnnotation)")
//    public void testAtTargetPointCut(){}

    //测试@tagregt
//    @Before(value = "testAtTargetPointCut()")
//    public void testAtTarget(){
//
//        logger.info("@target....");
//    }

    /**
     * @annotation ：匹配连接点被它参数指定的Annotation注解的方法。也就是说，所有被指定注解标注的方法都将匹配。
     * */
    @Pointcut("@annotation(com.example.demo.common.CzyAdviceAnnotation)")
    public void testAtAnnotationPointCut(){}

    @Before(value = "testAtAnnotationPointCut()")
    public void testAtAnnotation(){

        logger.info("@annotation....");
    }


    /**
     * this:用于向通知方法中传入代理对象的引用。
     * */
    @Before("executionPointCut() && this(proxy)")
    public void testThis(JoinPoint joinPoint, Object proxy){

        logger.info("joinPoint = " + joinPoint);
        logger.info("proxy = " + proxy);
    }

    /**
     * target: 用于向通知方法中传入目标对象的引用。
     * */
    @Before("executionPointCut() && target(target)")
    public void testTarget(JoinPoint joinPoint, Object target){

        logger.info("joinPoint = " + joinPoint);
        logger.info("target = " + target);
    }

    /**
     * args：用于将参数传入到通知方法中。
     */
    @Before("executionPointCut() && args(name)")
    public void testArgs(JoinPoint joinPoint, String name){

        logger.info("joinPoint = " + joinPoint);
        logger.info("name = " + name);
    }
}
