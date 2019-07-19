/**
 * @Title CardInfoEntity.java
 * @Package com.example.demo.dao.cardInfo.model
 * @Description 
 * @author weipeng
 * @date 2019-02-19 18:01:22
 * @version : V1.0
 */

package com.example.demo.dao.cardInfo.model;

import com.example.demo.config.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @ClassName CardInfoEntity
 * @Description Service
 * @author weipeng
 * @date 2019-02-19 18:01:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CardInfoEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * 银行卡号
	 */
	private String cardNo;

	/**
	 * 银行名称
	 */
	private String bankName;
}