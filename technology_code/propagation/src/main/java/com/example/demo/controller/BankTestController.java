package com.example.demo.controller;

import com.example.demo.config.AdusResponse;
import com.example.demo.facade.IBankCardFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test1")
public class BankTestController {
    @Resource
    private IBankCardFacade bankCardFacade;

    @PostMapping("/insertBankCard")
    public AdusResponse insertBankCard() {
        try{
            bankCardFacade.insertBankCard();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }


    @PostMapping("/insertBankCardAndActive")
    public AdusResponse insertBankCardAndActive() {
        try{
            bankCardFacade.insertBankCardAndActive();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertBankCardAndSendMail")
    public AdusResponse insertBankCardAndSendMail() {
        try{
            bankCardFacade.insertBankCardAndSendMail();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertBankCardAndOprLog")
    public AdusResponse insertBankCardAndOprLog() {
        try{
            bankCardFacade.insertBankCardAndOprLog();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertBankCardAndSelectThis")
    public AdusResponse insertBankCardAndSelectThis() {
        try{
            bankCardFacade.insertBankCardAndSelectThis();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertBankCardAndSelectService")
    public AdusResponse insertBankCardAndSelectService() {
        try{
            bankCardFacade.insertBankCardAndSelectService();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }
}