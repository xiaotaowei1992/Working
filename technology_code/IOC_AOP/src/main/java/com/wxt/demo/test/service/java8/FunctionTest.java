package com.wxt.demo.test.service.java8;

import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;

        //compose 函数先执行参数，然后执行调用者，而 andThen 先执行调用者，然后再执行参数。
        int value = name.andThen(square).apply(3);
        System.out.println("andThen value="+value);//36  3*2 * 3*2
        int value2 = name.compose(square).apply(3);
        System.out.println("compose value2="+value2);//18   3*3 * 2



        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object identity = Function.identity().apply(1);
        System.out.println(identity);
    }
}
