package com.wxt.comm.handler;

import com.wxt.comm.annotation.Sign;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class SignApiInterceptor {

    @Around("@annotation(anno)")
    public Object authenticate(ProceedingJoinPoint pjp, Sign anno) throws Throwable {
        Object result;
        log.info("controller之前处理");
        
        try {
            result = pjp.proceed();
        }catch (Exception e) {
            log.info("controller异常处理" + e);
            throw new RuntimeException(e);
        }
        log.info("controller之后处理");
        return result;
    }
}
