/**
 * @Title CardInfoService.java
 * @Package com.example.demo.dao.cardInfo.service.impl
 * @Description 
 * @author weipeng
 * @date 2019-02-19 18:01:22
 * @version : V1.0
 */

package com.example.demo.service.impl;

import com.example.demo.config.BaseService;
import com.example.demo.dao.cardInfo.mapper.CardInfoMapper;
import com.example.demo.dao.cardInfo.model.CardInfoEntity;
import com.example.demo.service.ICardInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName CardInfoService
 * @Description Service
 * @author weipeng
 * @date 2019-02-19 18:01:22
 */
@Slf4j
@Service
public class CardInfoService extends BaseService<CardInfoEntity> implements ICardInfoService {

	@Resource
	private CardInfoMapper cardInfoMapper;

	/**
	 * @Title insertCardInfo
	 * @Description 新增
	 * @param entityInfo
	 * @return 
	 */
	@Override
	public void insertCardInfo(CardInfoEntity entityInfo){
		cardInfoMapper.insert(entityInfo);
	}


	/**
	 * @Title updateCardInfo
	 * @Description 修改
	 * @param entityInfo
	 * @return 
	 */
	public void updateCardInfo(CardInfoEntity entityInfo){
		cardInfoMapper.update(entityInfo);
	}

}