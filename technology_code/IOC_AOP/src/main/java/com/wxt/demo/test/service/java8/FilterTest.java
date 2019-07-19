package com.wxt.demo.test.service.java8;

import com.wxt.demo.test.dao.Item;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FilterTest {
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("1")),
                new Item("banana", 20, new BigDecimal("1")),
                new Item("orang", 20, new BigDecimal("1")),
                new Item("watermelon", 10, new BigDecimal("1")),
                new Item("papaya", 20, new BigDecimal("1")),
                new Item("apple", 20, new BigDecimal("1")),
                new Item("banana", 10, new BigDecimal("1")),
                new Item("apple", 20, new BigDecimal("1"))
        );

        /**
         * 取前三个
         */
//        items.stream().limit(3).forEach(System.out :: println);

        /**
         * 根据条件过滤
         */
        List<Item> it = items.stream().filter(item -> item.getQty() > 10).collect(Collectors.toList());
        System.out.println(it);

        /**
         * 转成map需要确定key不能重复，否则报错
         */
//        Map<String, Item> itmap = items.stream().filter(item -> item.getQty() > 10).collect(Collectors.toMap(a -> a.getName(), a -> a));
//        System.out.println(itmap);


        /**
         * 过滤条件
         */
//        List<String> it = items.stream().filter(item-> item.getQty() >10).map(Item::getName).collect(Collectors.toList());
//        List<Item> its = items.stream().filter(item-> item.getQty() >10).collect(Collectors.toList());
//        System.out.println(it);
//        System.out.println(its);

        /**
         * 维度相同的，对应的字段相加
         */
        List<Item> newList = new ArrayList<>();
        items.stream().forEach(temp -> {
            Optional<Item> first = newList.stream().filter(item ->
                    temp.getName().equals(item.getName()) && temp.getQty()==item.getQty()
            ).findFirst();
            if (first.isPresent()){
                Item item = first.get();
                item.setPrice(Optional.ofNullable(item.getPrice()).orElse(BigDecimal.ZERO).add(Optional.of(temp.getPrice()).orElse(BigDecimal.ZERO)));
            }else {
                if(temp.getPrice() == null){
                    temp.setPrice(BigDecimal.ZERO);
                }
                newList.add(temp);
            }
        });
        System.out.println(newList);
    }
}
