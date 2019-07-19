/**
 * @Title IApiLogService.java
 * @Package com.shein.tss.apiLog.service
 * @Description 接口日志表
 * @author weipeng
 * @date 2019-01-15 09:18:19
 * @version : V1.0
 */

package com.example.demo.service;

import com.example.demo.config.IBaseService;
import com.example.demo.dao.user.model.UserEntity;

/**
 * @ClassName IUserService
 * @author weipeng
 * @date 2019-01-15 09:18:19
 */
public interface IUserService extends IBaseService<UserEntity> {

	/**
	 * @Title addRequired
	 * @Description 新增用户记录,开启REQUIRED事务
	 * @param userEntity
	 * @return
	 */
	void addRequired(UserEntity userEntity);

	/**
	 * @Title addRequiredException
	 * @Description 新增用户记录,开启REQUIRED事务，并抛出异常
	 * @param userEntity
	 * @return
	 */
	void addRequiredException(UserEntity userEntity);

	/**
	 * @Title addRequiresNew
	 * @Description 新增用户记录,开启REQUIRES_NEW事务
	 * @param userEntity
	 * @return
	 */
	void addRequiresNew(UserEntity userEntity);

	/**
	 * @Title addRequiresNew
	 * @Description 新增用户记录,开启REQUIRES_NEW事务，并抛出异常
	 * @param userEntity
	 * @return
	 */
	void addRequiresNewException(UserEntity userEntity);

	/**
	 * @Title addNested
	 * @Description 新增用户记录,开启NESTED事务
	 * @param userEntity
	 * @return
	 */
	void addNested(UserEntity userEntity);

	/**
	 * @Title addNestedException
	 * @Description 新增用户记录,开启NESTED事务，并抛出异常
	 * @param userEntity
	 * @return
	 */
	void addNestedException(UserEntity userEntity);

	/**
	 * @Title addMandatory
	 * @Description 新增用户记录,开启MANDATORY事务
	 * @param userEntity
	 * @return
	 */
	void addMandatory(UserEntity userEntity);

	/**
	 * @Title addNever
	 * @Description 新增用户记录,开启NEVER事务
	 * @param userEntity
	 * @return
	 */
	void addNever(UserEntity userEntity);
	/**
	 * @Title addSupports
	 * @Description 新增用户记录,开启SUPPORTS事务
	 * @param userEntity
	 * @return
	 */
	void addSupports(UserEntity userEntity);

	/**
	 * @Title addSupportsException
	 * @Description 新增用户记录,开启SUPPORTS事务，并抛出异常
	 * @param userEntity
	 * @return
	 */
	void addSupportsException(UserEntity userEntity);

	/**
	 * @Title addNotSupported
	 * @Description 新增用户记录,开启NOT_SUPPORTED事务
	 * @param userEntity
	 * @return
	 */
	void addNotSupported(UserEntity userEntity);

	/**
	 * @Title addNotSupportedException
	 * @Description 新增用户记录,开启NOT_SUPPORTED事务，并抛出异常
	 * @param userEntity
	 * @return
	 */
	void addNotSupportedException(UserEntity userEntity);

	/**
	 * 新增用户记录，service方法上不添加事务
	 * @param userEntity
	 */
	void add(UserEntity userEntity);

}