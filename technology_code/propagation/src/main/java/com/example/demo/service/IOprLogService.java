/**
 * @Title IOprLogService.java
 * @Package com.example.demo.dao.oprLog.service
 * @Description 
 * @author weipeng
 * @date 2019-02-20 17:23:48
 * @version : V1.0
 */

package com.example.demo.service;

import com.example.demo.config.IBaseService;
import com.example.demo.dao.oprLog.model.OprLogEntity;

/**
 * @ClassName IOprLogService
 * @Description Service
 * @author weipeng
 * @date 2019-02-20 17:23:48
 */
public interface IOprLogService extends IBaseService<OprLogEntity> {

	/**
	 * @Title insertOprLog
	 * @Description 新增
	 * @param entityInfo
	 * @return 
	 */
	void insertOprLog(OprLogEntity entityInfo);

}