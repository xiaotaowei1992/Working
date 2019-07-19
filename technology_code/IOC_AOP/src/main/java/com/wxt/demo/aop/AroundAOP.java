package com.wxt.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(2)
public class AroundAOP {

    @Pointcut("execution(public * com.wxt.demo.aop.AopTestController.test())")
    public void aroundTest(){}
    
    @Around("aroundTest()")
    public Object around(ProceedingJoinPoint pjp) throws  Throwable{
        log.info("[Aspect1] around advise 1");
        Object o;
        try {
            o =  pjp.proceed();
        }catch (Exception e) {
            log.info("[Aspect1] around advise2 异常处理" + e);
            throw new RuntimeException(e);
        }
        log.info("[Aspect1] around advise2");
        return o;
    }
    
}
