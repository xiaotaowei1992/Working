/**
 * @Title ApiLogEntity.java
 * @Package com.shein.tss.apiLog.model
 * @Description 接口日志表
 * @author weipeng
 * @date 2019-01-15 09:18:19
 * @version : V1.0
 */

package com.example.demo.dao.user.model;

import com.example.demo.config.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName User1Entity
 * @author weipeng
 * @date 2019-01-15 09:18:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * 用户姓名
	 */
	private String name;
}