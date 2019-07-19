/**
 * @Title BankCardFacade.java
 * @Package com.example.demo.dao.bankCard.facade.impl
 * @Description 
 * @author weipeng
 * @date 2019-02-19 17:56:18
 * @version : V1.0
 */

package com.example.demo.facade.impl;

import com.example.demo.config.BaseFacade;
import com.example.demo.dao.bankCard.model.BankCardEntity;
import com.example.demo.dao.oprLog.model.OprLogEntity;
import com.example.demo.facade.IBankCardFacade;
import com.example.demo.service.IBankCardService;
import com.example.demo.service.IOprLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BankCardFacade
 * @Description Service
 * @author weipeng
 * @date 2019-02-19 17:56:18
 */
@Slf4j
@Service
public class BankCardFacade extends BaseFacade<BankCardEntity> implements IBankCardFacade {

	@Resource
	private IBankCardService bankCardService;

	@Resource
	private IOprLogService oprLogService;

	@Resource
	private ThreadPoolTaskExecutor executor;

	/**
	 * @Title insertBankCard
	 * @Description 新增
	 * @return
	 */
	@Override
	@Transactional
	public void insertBankCard(){
		BankCardEntity bankCard = new BankCardEntity();
		bankCard.setCardNo("56782333333");
		bankCardService.insertBankCard(bankCard);
		//其他的业务
		try{
			bankCard.setIsActive(1);
			bankCardService.updateBankCard(bankCard);
			//其他的业务
		}catch (Exception e) {
			log.info("bank card register exception",e);
		}
	}

	/**
	 * @Title insertBankCard
	 * @Description 新增
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertBankCardAndOprLog(){
		BankCardEntity bankCard = new BankCardEntity();
		bankCard.setCardNo("567826634445");
		bankCardService.insertBankCard(bankCard);

		OprLogEntity oprLogEntity = new OprLogEntity();
		oprLogEntity.setAction("insert bank card");
		oprLogEntity.setOpr("admin");
		oprLogService.insertOprLog(oprLogEntity);

		throw new RuntimeException("exception");
	}

	/**
	 * @Title insertBankCardAndActive
	 * @Description 新增
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertBankCardAndActive(){
		BankCardEntity bankCard = new BankCardEntity();
		bankCard.setCardNo("56782666667");
		bankCardService.insertBankCard(bankCard);
		//其他的业务
		try{
			bankCard.setIsActive(1);
			bankCardService.cardActive(bankCard);
			//其他的业务
		}catch (Exception e) {
			log.info("exception",e);
		}
	}

    /**
     * @Title insertBankCard
     * @Description 新增
     * @return
     */
    @Override
    @Transactional
    public void insertBankCardAndSendMail(){
        BankCardEntity bankCard = new BankCardEntity();
        bankCard.setCardNo("567825543783");
        bankCardService.insertBankCard(bankCard);
        try{
			bankCardService.sendCardMail(bankCard);
		}catch(Exception e){
        	log.info("send mail exception",e);
		}
    }

	/**
	 * @Title insertBankCard
	 * @Description 新增
	 * @return
	 */
	@Override
	@Transactional
	public void insertBankCardAndSelectThis(){
		BankCardEntity bankCard = new BankCardEntity();
		bankCard.setCardNo("567825543783");
		bankCardService.insertBankCard(bankCard);
		bankCard.setIsActive(1);
		bankCardService.updateBankCard(bankCard);
		executor.execute(() -> selectBankCardCreate(bankCard));
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			log.error("error",e);
		}
		log.info("insertBankCardAndSelectThis thread is end");
	}

	/**
	 * @Title insertBankCard
	 * @Description 新增
	 * @return
	 */
	@Override
	@Transactional
	public void insertBankCardAndSelectService(){
		BankCardEntity bankCard = new BankCardEntity();
		bankCard.setCardNo("567825543783");
		bankCardService.insertBankCard(bankCard);
		bankCardService.selectBankCardCreate(bankCard);
	}


	/**
	 * @Title updateBankCard
	 * @Description 修改
	 * @param entityInfo
	 * @return 
	 */
	@Override
	public void updateBankCard(BankCardEntity entityInfo){
		bankCardService.updateBankCard(entityInfo);
	}

	public void selectBankCardCreate(BankCardEntity bankCard){
		Map<String, Object> param = new HashMap<>();
		param.put("cardNo",bankCard.getCardNo());
		BankCardEntity bankCardCreate = bankCardService.selectBankCard(param);
		System.out.println(bankCardCreate.getIsActive());
		log.info("bankCardCreate cardNo:"+bankCardCreate.getCardNo());
		log.info("selectBankCardCreate thread is end");
	}

}