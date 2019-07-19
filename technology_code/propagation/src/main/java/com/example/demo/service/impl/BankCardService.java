/**
 * @Title BankCardService.java
 * @Package com.example.demo.dao.bankCard.service.impl
 * @Description 
 * @author weipeng
 * @date 2019-02-19 17:56:18
 * @version : V1.0
 */

package com.example.demo.service.impl;

import com.example.demo.config.BaseService;
import com.example.demo.dao.bankCard.mapper.BankCardMapper;
import com.example.demo.dao.bankCard.model.BankCardEntity;
import com.example.demo.service.IBankCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BankCardService
 * @Description Service
 * @author weipeng
 * @date 2019-02-19 17:56:18
 */
@Slf4j
@Service
public class BankCardService extends BaseService<BankCardEntity> implements IBankCardService {

	@Resource
	private BankCardMapper bankCardMapper;

	/**
	 * @Title insertBankCard
	 * @Description 新增
	 * @param entityInfo
	 * @return 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertBankCard(BankCardEntity entityInfo){
		//新增
		bankCardMapper.insert(entityInfo);
	}

	/**
	 * @Title insertBankCardException
	 * @Description 新增
	 * @param entityInfo
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertBankCardException(BankCardEntity entityInfo){
		//新增
		bankCardMapper.insert(entityInfo);
	}


	/**
	 * @Title isActive
	 * @Description 激活
	 * @param entityInfo
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void cardActive(BankCardEntity entityInfo){
		bankCardMapper.update(entityInfo);
		throw new RuntimeException("active exception");
	}
	/**
	 * @Title updateBankCard
	 * @Description 修改
	 * @param entityInfo
	 * @return 
	 */
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void updateBankCard(BankCardEntity entityInfo){
		bankCardMapper.update(entityInfo);
		//throw new RuntimeException("active exception");
	}

	/**
	 * @Title selectBankCard
	 * @Description 查询银行卡
	 * @param param
	 * @return
	 */
	@Override
	public BankCardEntity selectBankCard(Map<String, Object> param){
		return bankCardMapper.selectEntity(param);
	}

	/**
	 * @Title updateBankCard
	 * @Description 修改
	 * @param entityInfo
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void updateBankCardExcepion(BankCardEntity entityInfo){
		bankCardMapper.update(entityInfo);
		throw new RuntimeException("exception");
	}

	/**
	 * @Title sendCardMail
	 * @Description 发送邮件
	 * @param entityInfo
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void sendCardMail(BankCardEntity entityInfo){
		log.info("create bank card success,cardNo:"+entityInfo.getCardNo());
		throw new RuntimeException("send main time out");
	}
	@Override
	@Transactional
	public void selectBankCardCreate(BankCardEntity bankCard){
		try{
			Thread.sleep(3000);
		}catch(Exception e){
			log.error("error",e);
		}
		Map<String, Object> param = new HashMap<>();
		param.put("cardNo",bankCard.getCardNo());
		BankCardEntity bankCardCreate = bankCardMapper.selectEntity(param);
		System.out.println(bankCardCreate.getIsActive());
		log.info("bankCardCreate cardNo:"+bankCardCreate.getCardNo());
		log.info("selectBankCardCreate thread is end");
	}
}