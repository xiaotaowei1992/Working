/**
 * @Title IOprLogFacade.java
 * @Package com.example.demo.dao.oprLog.facade
 * @Description 
 * @author weipeng
 * @date 2019-02-20 17:23:48
 * @version : V1.0
 */

package com.example.demo.facade;

import com.example.demo.config.IBaseFacade;
import com.example.demo.dao.oprLog.model.OprLogEntity;

/**
 * @ClassName IOprLogFacade
 * @Description Service
 * @author weipeng
 * @date 2019-02-20 17:23:48
 */
public interface IOprLogFacade extends IBaseFacade<OprLogEntity> {

	/**
	 * @Title insertOprLog
	 * @Description 新增
	 * @param entityInfo
	 * @return 
	 */
	void insertOprLog(OprLogEntity entityInfo);

}