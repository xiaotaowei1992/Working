/**
 * @Title UserService.java
 * @Package com.example.demo.service.impl
 * @Description UserService
 * @author weipeng
 * @date 2019-01-15 09:18:19
 * @version : V1.0
 */

package com.example.demo.service.impl;

import com.example.demo.dao.user.mapper.UserMapper;
import com.example.demo.dao.user.model.UserEntity;
import com.example.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * @ClassName UserService
 * @author weipeng
 * @date 2019-01-15 09:18:19
 */
@Slf4j
@Service
public class UserService implements IUserService {

	@Resource
	private UserMapper userMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addRequired(UserEntity userEntity){
		userMapper.insertUser(userEntity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addRequiredException(UserEntity userEntity){
		userMapper.insertUser(userEntity);
		throw new RuntimeException();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addRequiresNew(UserEntity userEntity){
		userMapper.insertUser(userEntity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addRequiresNewException(UserEntity userEntity){
		userMapper.insertUser(userEntity);
		throw new RuntimeException();
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void addMandatory(UserEntity userEntity){
		userMapper.insertUser(userEntity);
	}

	@Override
	@Transactional(propagation = Propagation.NEVER)
	public void addNever(UserEntity userEntity){
		userMapper.insertUser(userEntity);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void addSupports(UserEntity userEntity){
		userMapper.insertUser(userEntity);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void addSupportsException(UserEntity userEntity){
		userMapper.insertUser(userEntity);
		throw new RuntimeException();
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void addNested(UserEntity userEntity){
		userMapper.insertUser(userEntity);
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void addNestedException(UserEntity userEntity){
		userMapper.insertUser(userEntity);
		throw new RuntimeException();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void addNotSupported(UserEntity userEntity){
		userMapper.insertUser(userEntity);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void addNotSupportedException(UserEntity userEntity){
		userMapper.insertUser(userEntity);
		throw new RuntimeException();
	}

	@Override
	public void add(UserEntity userEntity){
		userMapper.insertUser(userEntity);
	}
}