/**
 * @Title BankCardEntity.java
 * @Package com.example.demo.dao.bankCard.model
 * @Description 
 * @author weipeng
 * @date 2019-02-19 17:56:18
 * @version : V1.0
 */

package com.example.demo.dao.bankCard.model;

import com.example.demo.config.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName BankCardEntity
 * @Description Service
 * @author weipeng
 * @date 2019-02-19 17:56:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BankCardEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * 银行卡号
	 */
	private String cardNo;

	/**
	 * 是否激活
	 */
	private Integer isActive;
}