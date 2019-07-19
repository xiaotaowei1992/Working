/**
 * @Title IBankCardService.java
 * @Package com.example.demo.dao.bankCard.service
 * @Description 
 * @author weipeng
 * @date 2019-02-19 17:56:18
 * @version : V1.0
 */

package com.example.demo.service;

import com.example.demo.config.IBaseService;
import com.example.demo.dao.bankCard.model.BankCardEntity;

import java.util.Map;

/**
 * @ClassName IBankCardService
 * @Description Service
 * @author weipeng
 * @date 2019-02-19 17:56:18
 */
public interface IBankCardService extends IBaseService<BankCardEntity> {

	/**
	 * @Title insertBankCard
	 * @Description 新增
	 * @param entityInfo
	 * @return 
	 */
	void insertBankCard(BankCardEntity entityInfo);

	/**
	 * @Title insertBankCard
	 * @Description 新增
	 * @param entityInfo
	 * @return
	 */
	void insertBankCardException(BankCardEntity entityInfo);


	/**
	 * @Title updateBankCard
	 * @Description 修改
	 * @param entityInfo
	 * @return 
	 */
	void updateBankCard(BankCardEntity entityInfo);

	/**
	 * @Title selectBankCard
	 * @Description 查询银行卡
	 * @param param
	 * @return
	 */
	BankCardEntity selectBankCard(Map<String, Object> param);

	/**
	 * @Title cardActive
	 * @Description 激活
	 * @param entityInfo
	 * @return
	 */
	void cardActive(BankCardEntity entityInfo);

	/**
	 * @Title updateBankCard
	 * @Description 修改
	 * @param entityInfo
	 * @return
	 */
	void updateBankCardExcepion(BankCardEntity entityInfo);

	/**
	 * @Title sendCardMail
	 * @Description 发送邮件
	 * @param entityInfo
	 * @return
	 */
	void sendCardMail(BankCardEntity entityInfo);

	/**
	 * 查询新增的银行卡号
	 * @param bankCard
	 */
	void selectBankCardCreate(BankCardEntity bankCard);

}