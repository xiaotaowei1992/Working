/**
 * @Title OprLogEntity.java
 * @Package com.example.demo.dao.oprLog.model
 * @Description 
 * @author weipeng
 * @date 2019-02-20 17:23:48
 * @version : V1.0
 */

package com.example.demo.dao.oprLog.model;

import com.example.demo.config.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName OprLogEntity
 * @Description Service
 * @author weipeng
 * @date 2019-02-20 17:23:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OprLogEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * 操作人
	 */
	private String opr;

	/**
	 * 操作动作
	 */
	private String action;
}