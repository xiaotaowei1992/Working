package com.wxt.demo.proxy;

import java.lang.reflect.Method;

public class ProxyBusiness implements IBusiness, IBusiness2 {

    private LogInvocationHandler h;
    
    @Override
    public void doSomeThing() {
        try {
            Method m = (h.target).getClass().getMethod("doSomeThing", null);
            h.invoke(this, m, null);
        } catch (Throwable e) {
            // 异常处理（略） 
        }
    }

    @Override
    public void doSomeThing2() {
        try {
            Method m = (h.target).getClass().getMethod("doSomeThing2", null);
            h.invoke(this, m, null);
        } catch (Throwable e) {
            // 异常处理（略） 
        }
    }
    
    public ProxyBusiness(LogInvocationHandler h) {
        this.h = h;
    }

    //测试用 
    public static void main(String[] args) {
        //构建AOP的Advice 
        LogInvocationHandler handler = new LogInvocationHandler(new Business());
        new ProxyBusiness(handler).doSomeThing();
        new ProxyBusiness(handler).doSomeThing2();
    }
}
