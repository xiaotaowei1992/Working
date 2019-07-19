package com.wxt.demo.beaninit;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanInit implements InitializingBean, DisposableBean  {
    public BeanInit() {
        super();
        System.out.println("@BeanInit-init-method：初始化构造函数");
    }
    
    @PostConstruct
    public void init1(){
        System.out.println("@BeanInit-init-method：postConstruct");
    }
    
    @Override
    public void afterPropertiesSet() {
        System.out.println("@BeanInit-init-method：afterPropertiesSet");
    }

    public void init(){
        System.out.println("@BeanInit-init-method：init-method");
    }
    
    
    
    @PreDestroy
    public void destroy1(){
        System.out.println("@BeanInit-destory-method：PreDestroy");
    }
    
   
    @Override
    public void destroy(){
        System.out.println("@BeanInit-destory-method：destroy");
    }

    public void destroyMethod(){
        System.out.println("@BeanInit-destory-method：destroyMethod");
    }
}
