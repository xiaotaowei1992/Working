package com.wxt.demo.beaninit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.wxt.demo.beaninit")
public class BeanTest {

    /**
     * bean1使用 @BeanInit(initMethod="init",destroyMethod="destroy") 方式实现初始化和销毁
     */
    @Bean(initMethod="init",destroyMethod="destroyMethod")
    BeanInit beanOne(){
        return new BeanInit();
    }
    
}
