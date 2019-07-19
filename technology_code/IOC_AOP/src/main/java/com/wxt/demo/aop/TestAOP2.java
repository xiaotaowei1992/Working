package com.wxt.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(1)
public class TestAOP2 {
    @Pointcut("execution(public * com.wxt.demo.aop.AopTestController.test())")
    public void test2(){}

    /**
     * 声明一个前置通知
     */
    @Before("test2()")
    public void beforeAdvide(JoinPoint point){
        log.info("test2  Before() 触发了前置通知！");
    }

    /**
     * 声明一个后置通知
     */
    @After("test2()")
    public void afterAdvie(JoinPoint point){
        log.info("test2 After() 触发了后置通知，抛出异常也会被触发！");
    }

    /**
     * 声明一个返回后通知
     */
    @AfterReturning(pointcut="test2()", returning="ret")
    public void afterReturningAdvice(JoinPoint point, Object ret){
        log.info("test2 AfterReturning() 触发了返回后通知，抛出异常时不被触发，返回值为：" + ret);
    }

    /**
     * 声明一个异常通知
     */
    @AfterThrowing(pointcut="test2()", throwing="throwing")
    public void afterThrowsAdvice(JoinPoint point, RuntimeException throwing){
        log.info("test2 AfterThrowing() 触发了异常通知，抛出了异常！");
    }
}
