package com.wxt.demo.test.service.java8;

import com.wxt.demo.test.dao.Item;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class List2Maplist {
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item("1", 10, new BigDecimal("9.99")),
                new Item("2", 20, new BigDecimal("19.99")),
                new Item("3", 10, new BigDecimal("29.99")),
                new Item("4", 10, new BigDecimal("29.99")),
                new Item("4", 20, new BigDecimal("9.99")),
                new Item("5", 10, new BigDecimal("9.99")),
                new Item("5", 20, new BigDecimal("19.99")),
                new Item("5", 30, new BigDecimal("9.99"))
        );

        Map<String,Item> map = items.stream().collect(Collectors.toMap(a ->a.getName()+"-"+a.getQty(), a->a));
        System.out.println(map);

    }
}
