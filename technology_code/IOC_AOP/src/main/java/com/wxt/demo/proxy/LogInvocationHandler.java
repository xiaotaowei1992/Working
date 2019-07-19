package com.wxt.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 打印日志的切面
 */
public class LogInvocationHandler implements InvocationHandler {
    public  Object target;//目标对象

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行织入的日志，你可以控制哪些方法执行切入逻辑
        if (method.getName().equals("doSomeThing")) {
            System.out.println("记录日志1");
        }
        if (method.getName().equals("doSomeThing2")) {
            System.out.println("记录日志2");
        }
        
        //执行原有逻辑
        Object recv = method.invoke(target, args);
        
        return recv;
    }
  
}
