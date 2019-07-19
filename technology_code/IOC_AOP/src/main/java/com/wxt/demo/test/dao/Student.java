package com.wxt.demo.test.dao;

import java.util.function.Function;

public class Student {

    public String name;
    public int age;
    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }
    public  String customShow(Function<Student,String> fun){
        return fun.apply(this);
    }
    
    public  Integer customShow2(Function<Student,Integer> fun){
        return fun.apply(this);
    }
    
}
