package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan({"com.example.demo.dao.user.mapper","com.example.demo.dao.bankCard.mapper",
        "com.example.demo.dao.cardInfo.mapper","com.example.demo.dao.oprLog.mapper"})
public class DemoApplication {

    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager) {
        System.out.println(platformTransactionManager.getClass().getName());
        return new Object();
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\lingtian");
        SpringApplication.run(DemoApplication.class, args);
    }

}

