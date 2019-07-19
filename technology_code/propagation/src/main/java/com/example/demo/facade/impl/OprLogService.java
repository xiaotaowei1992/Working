/**
 * @Title OprLogService.java
 * @Package com.example.demo.dao.oprLog.service.impl
 * @Description 
 * @author weipeng
 * @date 2019-02-20 17:23:48
 * @version : V1.0
 */

package com.example.demo.facade.impl;

import com.example.demo.config.BaseService;
import com.example.demo.dao.oprLog.mapper.OprLogMapper;
import com.example.demo.dao.oprLog.model.OprLogEntity;
import com.example.demo.service.IOprLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName OprLogService
 * @Description Service
 * @author weipeng
 * @date 2019-02-20 17:23:48
 */
@Slf4j
@Service
public class OprLogService extends BaseService<OprLogEntity> implements IOprLogService {

	@Resource
	private OprLogMapper oprLogMapper;

	/**
	 * @Title insertOprLog
	 * @Description 新增
	 * @param entityInfo
	 * @return 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertOprLog(OprLogEntity entityInfo){
		//新增
		oprLogMapper.insert(entityInfo);
	}

}