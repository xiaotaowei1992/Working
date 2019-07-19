package com.example.demo.facade.impl;

import com.example.demo.dao.user.model.UserEntity;
import com.example.demo.facade.IUserFacade;
import com.example.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Slf4j
@Service
public class UserFacade implements IUserFacade {
    @Resource
    private IUserService userService;
    /**
     * 外围方法不开启事务
     */
    @Override
    public void noTransactionExceptionRequiredRequired(){
        UserEntity user1=new UserEntity();
        user1.setName("张三001");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四001");
        userService.addRequired(user2);
        throw new RuntimeException();
    }

    /**
     * 外围方法不开启事务
     */
    @Override
    public void noTransactionRequiredRequiredException(){
        UserEntity user1=new UserEntity();
        user1.setName("张三002");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四002");
        userService.addRequiredException(user2);
    }

    /**
     * 外围方法开启事务
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionExceptionRequiredRequired(){
        UserEntity user1=new UserEntity();
        user1.setName("张三003");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四003");
        userService.addRequired(user2);

        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiredException(){
        UserEntity user1=new UserEntity();
        user1.setName("张三004");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四004");
        userService.addRequiredException(user2);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiredExceptionTry(){
        UserEntity user1=new UserEntity();
        user1.setName("张三005");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四005");
        try {
            userService.addRequiredException(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }

    /****************************Propagation.REQUIRES_NEW************************************/

    @Override
    public void noTransactionExceptionRequiresNewRequiresNew(){
        UserEntity user1=new UserEntity();
        user1.setName("张三006");
        userService.addRequiresNew(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四006");
        userService.addRequiresNew(user2);

        throw new RuntimeException();
    }

    @Override
    public void noTransactionRequiresNewRequiresNewException(){
        UserEntity user1=new UserEntity();
        user1.setName("张三007");
        userService.addRequiresNew(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四007");
        userService.addRequiresNewException(user2);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionExceptionRequiredRequiresNewRequiresNew(){
        UserEntity user1=new UserEntity();
        user1.setName("张三008");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四008");
        userService.addRequiresNew(user2);

        UserEntity user3=new UserEntity();
        user3.setName("王五008");
        userService.addRequiresNew(user3);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiresNewRequiresNewException(){
        UserEntity user1=new UserEntity();
        user1.setName("张三009");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四009");
        userService.addRequiresNew(user2);

        UserEntity user3=new UserEntity();
        user3.setName("王五009");
        userService.addRequiresNewException(user3);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredRequiresNewRequiresNewExceptionTry(){
        UserEntity user1=new UserEntity();
        user1.setName("张三010");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四010");
        userService.addRequiresNew(user2);

        UserEntity user3=new UserEntity();
        user3.setName("王五010");
        try {
            userService.addRequiresNewException(user3);
        } catch (Exception e) {
            System.out.println("回滚");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredNestNestException(){
        UserEntity user1=new UserEntity();
        user1.setName("张三011");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四011");
        userService.addNested(user2);

        UserEntity user3=new UserEntity();
        user3.setName("王五011");
        userService.addNestedException(user3);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionRequiredNestNestExceptionTry(){
        UserEntity user2=new UserEntity();
        user2.setName("李四012");
        userService.addNested(user2);
        throw new RuntimeException("error");
    }

    @Override
    public void noTransactionMandatory(){
        UserEntity user=new UserEntity();
        user.setName("李四013");
        userService.addMandatory(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionMandatory(){
        UserEntity user=new UserEntity();
        user.setName("李四014");
        userService.addMandatory(user);
    }

    @Override
    public void noTransactionNever(){
        UserEntity user=new UserEntity();
        user.setName("李四015");
        userService.addNever(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionNever(){
        UserEntity user=new UserEntity();
        user.setName("李四016");
        userService.addNever(user);
    }

    @Override
    public void noTransactionSupportsException(){
        UserEntity user=new UserEntity();
        user.setName("李四017");
        userService.addSupportsException(user);
    }

    @Override
    @Transactional
    public void transactionSupportsException(){
        UserEntity user=new UserEntity();
        user.setName("李四018");
        userService.addSupportsException(user);
    }

    @Override
    public void noTransactionNotSupportedException(){
        UserEntity user2=new UserEntity();
        user2.setName("李四019");
        userService.addNotSupportedException(user2);
    }

    @Override
    @Transactional
    public void transactionExceptionRequiredNotSupported() throws RuntimeException{
        UserEntity user2=new UserEntity();
        user2.setName("李四020");
        userService.addNotSupported(user2);
        throw new RuntimeException("异常");
    }

    @Override
    @Transactional
    public void transactionExceptionRequiredNotSupportedException(){
        UserEntity user1=new UserEntity();
        user1.setName("张三021");
        userService.addRequired(user1);

        UserEntity user2=new UserEntity();
        user2.setName("李四021");
        userService.addNotSupportedException(user2);
    }

    @Override
    @Transactional
    public void transactionTest(){
        UserEntity user2=new UserEntity();
        user2.setName("李四022");
        userService.addNotSupportedException(user2);
    }

    /**
     * 用来测试所以在同一个类中一个方法调用另一个方法有事务的方法，事务是不会起作用的
     */
    @Override
    public void transactionMethodCallTest(){
        transactionMethodCall();
    }

    @Transactional
    public void transactionMethodCall(){
        UserEntity user = new UserEntity();
        user.setName("李四023");
        userService.add(user);
        throw new RuntimeException("异常");
    }
}
