package com.example.demo.controller;

import com.example.demo.config.AdusResponse;
import com.example.demo.facade.IUserFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test2")
public class MethodCallTestController {
    @Resource
    private IUserFacade userFacade;

    @PostMapping("/index")
    public AdusResponse home() {
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/method")
    public AdusResponse transactionMethodCallTest() {
        try{
            userFacade.transactionMethodCallTest();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", null);
        }
        return new AdusResponse("1", "success", null);
    }

}