/**
 * @Title IBankCardFacade.java
 * @Package com.example.demo.dao.bankCard.facade
 * @Description 
 * @author weipeng
 * @date 2019-02-19 17:56:18
 * @version : V1.0
 */

package com.example.demo.facade;

import com.example.demo.config.IBaseFacade;
import com.example.demo.dao.bankCard.model.BankCardEntity;

/**
 * @ClassName IBankCardFacade
 * @Description Service
 * @author weipeng
 * @date 2019-02-19 17:56:18
 */
public interface IBankCardFacade extends IBaseFacade<BankCardEntity> {

	/**
	 * @Title insertBankCard
	 * @Description 新增
	 * @return
	 */
	void insertBankCard();


	/**
	 * @Title updateBankCard
	 * @Description 修改
	 * @param entityInfo
	 * @return 
	 */
	void updateBankCard(BankCardEntity entityInfo);

	/**
	 * @Title insertBankCardAndActive
	 * @Description 新增
	 * @return
	 */
	void insertBankCardAndActive();

	/**
	 * @Title insertBankCardAndOprLog
	 * @Description 新增
	 * @return
	 */
	void insertBankCardAndOprLog();

	/**
	 * @Title insertBankCardAndSendMail
	 * @Description 新增
	 * @return
	 */
	void insertBankCardAndSendMail();

	/**
	 * @Title insertBankCardAndSelectService
	 * @Description 新增
	 * @return
	 */
	void insertBankCardAndSelectService();

	/**
	 * @Title insertBankCardAndSelectThis
	 * @Description 新增
	 * @return
	 */
	void insertBankCardAndSelectThis();

}