package com.example.demo.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author: weixiaotao
 * @ClassName BaseEntity
 * @Date: 2019/3/18 09:57
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends BasicEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 操作用户Id
	 */
	private Integer optId;

	/**
	 * 操作用户名称
	 */
	private String optName;

	/**
	 * 添加时间
	 */
	private Date addTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 标识(是否有效 0有效；-1无效)
	 */
	private Integer mark;

	/**
	 * @Title initAddUpdateTime
	 * @Description 设置新增、修改时间
	 */
	public void initAddUpdateTime() {
		setAddTime(new Date());
		setUpdateTime(new Date());
	}

}
