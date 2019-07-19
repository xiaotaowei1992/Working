/**
 * @Title OprLogFacade.java
 * @Package com.example.demo.dao.oprLog.facade.impl
 * @Description 
 * @author weipeng
 * @date 2019-02-20 17:23:48
 * @version : V1.0
 */

package com.example.demo.facade.impl;

import com.example.demo.config.BaseFacade;
import com.example.demo.dao.oprLog.model.OprLogEntity;
import com.example.demo.facade.IOprLogFacade;
import com.example.demo.service.IOprLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName OprLogFacade
 * @Description Service
 * @author weipeng
 * @date 2019-02-20 17:23:48
 */
@Slf4j
@Service
public class OprLogFacade extends BaseFacade<OprLogEntity> implements IOprLogFacade {

	@Resource
	private IOprLogService oprLogService;

	/**
	 * @Title insertOprLog
	 * @Description 新增
	 * @param entityInfo
	 * @return 
	 */
	@Override
	public void insertOprLog(OprLogEntity entityInfo){
		oprLogService.insertOprLog(entityInfo);
	}

}