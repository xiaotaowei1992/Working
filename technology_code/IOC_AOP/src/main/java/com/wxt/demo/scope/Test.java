package com.wxt.demo.scope;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Singleton(单例)和Prototype(原型)的区别
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(ScopeBean.class);
        ScopeBean scopeBean1=ctx.getBean(ScopeBean.class);
        ScopeBean scopeBean2=ctx.getBean(ScopeBean.class);
        System.out.println(scopeBean1==scopeBean2);//单例的时候是true，原型的时候是false
    }
}
