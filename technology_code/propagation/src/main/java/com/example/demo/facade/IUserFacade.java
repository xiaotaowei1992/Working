/**
 * @Title IApiLogService.java
 * @Package com.shein.tss.apiLog.service
 * @Description 接口日志表
 * @author weipeng
 * @date 2019-01-15 09:18:19
 * @version : V1.0
 */

package com.example.demo.facade;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName IUserFacade
 * @author weipeng
 * @date 2019-01-15 09:18:19
 */
public interface IUserFacade{
	/**
	 * 外围方法不开启事务，两个独立的事务REQUIRED,外围方法抛出异常
	 */
	void noTransactionExceptionRequiredRequired();

	/**
	 * 外围方法不开启事务，两个独立的事务REQUIRED，并且一个内部方法抛出异常
	 */
	void noTransactionRequiredRequiredException();

	/**
	 * 外围方法开启事务,两个独立的事务REQUIRED,外围方法抛出异常
	 */
	void transactionExceptionRequiredRequired();

	/**
	 * 外围方法开启事务，两个独立的事务REQUIRED，并且一个内部方法抛出异常
	 */
	void transactionRequiredRequiredException();

	/**
	 * 外围方法开启事务，两个独立的事务REQUIRED，一个内部方法抛出异常并且捕获其中一个事务抛出的异常
	 */
	void transactionRequiredRequiredExceptionTry();

	/**
	 * 外围方法不开启事务，两个独立的事务REQUIRES_NEW,外围方法抛出异常
	 */
	void noTransactionExceptionRequiresNewRequiresNew();

	/**
	 * 外围方法不开启事务，两个独立的事务Propagation.REQUIRES_NEW，一个内部方法抛出异常
	 */
	void noTransactionRequiresNewRequiresNewException();

	/**
	 * 外围方法开启事务，三个独立的事务REQUIRED、REQUIRES_NEW、REQUIRES_NEW，外围方法抛出异常
	 */
	void transactionExceptionRequiredRequiresNewRequiresNew();

	/**
	 * 外围方法开启事务，三个独立的事务REQUIRED、REQUIRES_NEW、REQUIRES_NEW，一个内部方法抛出异常
	 */
	void transactionRequiredRequiresNewRequiresNewException();

	/**
	 *  外围方法开启事务，三个独立的事务REQUIRED、REQUIRES_NEW、REQUIRES_NEW，一个内部方法抛出异常并且捕获其中一个事务抛出的异常
	 */
	void transactionRequiredRequiresNewRequiresNewExceptionTry();

	/**
	 *  外围方法开启事务，三个独立的事务REQUIRED、NESTED、NESTED，一个内部方法抛出异常
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void transactionRequiredNestNestException();

	/**
	 *  外围方法开启事务，三个独立的事务REQUIRED、NESTED、NESTED，NESTED内部方法抛出异常并且捕获其中一个事务抛出的异常
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void transactionRequiredNestNestExceptionTry();

	/**
	 * 事务PROPAGATION_MANDATORY
	 */
	void transactionMandatory();

	/**
	 * 事务PROPAGATION_NEVER，无外围事务
	 */
	void noTransactionMandatory();

	/**
	 * 事务PROPAGATION_MANDATORY，无外围事务
	 */
	void noTransactionNever();

	/**
	 * 事务PROPAGATION_NEVER
	 */
	void transactionNever();

	/**
	 * 事务PROPAGATION_SUPPORTS
	 */
	void noTransactionSupportsException();

	/**
	 * 事务PROPAGATION_SUPPORTS
	 */
	void transactionSupportsException();

	/**
	 * 事务PAGATION_NOT_SUPPORTED
	 */
	void noTransactionNotSupportedException();

	/**
	 * 事务REQUIRED和PAGATION_NOT_SUPPORTED
	 */
	void transactionExceptionRequiredNotSupported() throws Exception;

	/**
	 * 事务PAGATION_NOT_SUPPORTED
	 */
	void transactionExceptionRequiredNotSupportedException();
	/**
	 * 测试
	 */
	void transactionTest();

	/**
	 * 用来测试所以在同一个类中一个方法调用另一个方法有事务的方法，事务是不会起作用的
	 */
	void transactionMethodCallTest();
}