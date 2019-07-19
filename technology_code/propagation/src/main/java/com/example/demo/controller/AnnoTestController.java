package com.example.demo.controller;

import com.example.demo.config.AdusResponse;
import com.example.demo.facade.IUserFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class AnnoTestController {
    @Resource
    private IUserFacade userFacade;

    @PostMapping("/index")
    public AdusResponse home() {
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser1")
    public AdusResponse insertUser1() {
        try{
            userFacade.noTransactionExceptionRequiredRequired();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser2")
    public AdusResponse insertUser2() {
        try{
            userFacade.noTransactionRequiredRequiredException();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser3")
    public AdusResponse insertUser3() {
        try{
            userFacade.transactionExceptionRequiredRequired();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser4")
    public AdusResponse insertUser4() {
        try{
            userFacade.transactionRequiredRequiredException();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser5")
    public AdusResponse insertUser5() {
        try{
            userFacade.transactionRequiredRequiredExceptionTry();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser6")
    public AdusResponse insertUser6() {
        try{
            userFacade.noTransactionExceptionRequiresNewRequiresNew();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser7")
    public AdusResponse insertUser7() {
        try{
            userFacade.noTransactionRequiresNewRequiresNewException();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser8")
    public AdusResponse insertUser8() {
        try{
            userFacade.transactionExceptionRequiredRequiresNewRequiresNew();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser9")
    public AdusResponse insertUser9() {
        try{
            userFacade.transactionRequiredRequiresNewRequiresNewException();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser10")
    public AdusResponse insertUser10() {
        try{
            userFacade.transactionRequiredRequiresNewRequiresNewExceptionTry();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser11")
    public AdusResponse insertUser11() {
        try{
            userFacade.transactionRequiredNestNestException();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }


    @PostMapping("/insertUser12")
    public AdusResponse insertUser12() {
        try{
            userFacade.transactionRequiredNestNestExceptionTry();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser13")
    public AdusResponse insertUser13() {
        try{
            userFacade.noTransactionMandatory();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser14")
    public AdusResponse insertUser14() {
        try{
            userFacade.transactionMandatory();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser15")
    public AdusResponse insertUser15() {
        try{
            userFacade.noTransactionNever();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }
    @PostMapping("/insertUser16")
    public AdusResponse insertUser16() {
        try{
            userFacade.transactionNever();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser17")
    public AdusResponse insertUser17() {
        try{
            userFacade.noTransactionSupportsException();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser18")
    public AdusResponse insertUser18() {
        try{
            userFacade.transactionSupportsException();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser19")
    public AdusResponse insertUser19() {
        try{
            userFacade.noTransactionNotSupportedException();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser20")
    public AdusResponse insertUser20() {
        try{
            userFacade.transactionExceptionRequiredNotSupported();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }

    @PostMapping("/insertUser21")
    public AdusResponse insertUser21() {
        try{
            userFacade.transactionExceptionRequiredNotSupportedException();
        }catch (Exception e){
            e.printStackTrace();
            return new AdusResponse("0", "exception", e.getMessage());
        }
        return new AdusResponse("1", "success", null);
    }
}