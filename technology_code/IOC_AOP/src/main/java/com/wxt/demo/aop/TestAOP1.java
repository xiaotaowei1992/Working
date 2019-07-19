package com.wxt.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(3)
public class TestAOP1 {
    
    @Pointcut("execution(public * com.wxt.demo.aop.AopTestController.test())")
    public void test1(){}

    /**
     * 声明一个前置通知
     */
    @Before("test1()")
    public void beforeAdvide(JoinPoint point){
        log.info("test1  Before() 触发了前置通知！");
    }

    /**
     * 声明一个后置通知
     */
    @After("test1()")
    public void afterAdvie(JoinPoint point){
        log.info("test1 After() 触发了后置通知，抛出异常也会被触发！");
    }

    /**
     * 声明一个返回后通知
     */
    @AfterReturning(pointcut="test1()", returning="ret")
    public void afterReturningAdvice(JoinPoint point, Object ret){
        log.info("test1 AfterReturning() 触发了返回后通知，抛出异常时不被触发，返回值为：" + ret);
    }

    /**
     * 声明一个异常通知
     */
    @AfterThrowing(pointcut="test1()", throwing="throwing")
    public void afterThrowsAdvice(JoinPoint point, RuntimeException throwing){
        log.info("test1 AfterThrowing() 触发了异常通知，抛出了异常！");
    }

    
//    @Before("om.wxt.demo.aop.PointCut.pointcut()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        log.info("test1 切入点前置方法");
//        log.info(Arrays.toString(joinPoint.getArgs()));
//    }
}
