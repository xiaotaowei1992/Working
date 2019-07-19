/**
 * @Title ICardInfoService.java
 * @Package com.example.demo.dao.cardInfo.service
 * @Description 
 * @author weipeng
 * @date 2019-02-19 18:01:22
 * @version : V1.0
 */

package com.example.demo.service;

import com.example.demo.config.IBaseService;
import com.example.demo.dao.cardInfo.model.CardInfoEntity;

/**
 * @ClassName ICardInfoService
 * @Description Service
 * @author weipeng
 * @date 2019-02-19 18:01:22
 */
public interface ICardInfoService extends IBaseService<CardInfoEntity> {

	/**
	 * @Title insertCardInfo
	 * @Description 新增
	 * @param entityInfo
	 * @return 
	 */
	void insertCardInfo(CardInfoEntity entityInfo);


	/**
	 * @Title updateCardInfo
	 * @Description 修改
	 * @param entityInfo
	 * @return 
	 */
	void updateCardInfo(CardInfoEntity entityInfo);

}