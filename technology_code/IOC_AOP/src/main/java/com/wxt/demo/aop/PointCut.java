package com.wxt.demo.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class PointCut {

    @Pointcut("execution(public * com.wxt.demo.aop.AopTestController.test())")
    public void pointcut(){}
    
}
