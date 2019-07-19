package com.wxt.demo.aop;

import com.wxt.comm.annotation.Sign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aop")
public class AopTestController {
    @GetMapping(value = "/test")
    public Map<String,Object> test(){
        Map<String,Object> resultMap = new HashMap<>(1);

        resultMap.put("xcc","testAop");
        System.out.println("执行方法------------------------------------------------------"+resultMap.toString());
//        Integer s = null;
//        s.toString();
        return resultMap;
    }

    @GetMapping(value = "/testSign")
    @Sign
    public Map<String,Object> testSign() throws Exception {
        Map<String,Object> resultMap = new HashMap<>(1);
        resultMap.put("xcc","testSign");
        System.out.println("执行方法------------------------------------------------------"+resultMap.toString());
        Integer s = 1;
        if(s == 1){
            throw new Exception("错误");
        }
        return resultMap;
    }
}
