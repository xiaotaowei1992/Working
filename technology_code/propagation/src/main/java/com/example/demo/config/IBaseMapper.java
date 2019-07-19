package com.example.demo.config;

import java.util.List;
import java.util.Map;

/**
 * @Author: weixiaotao
 * @ClassName IBaseMapper
 * @Date: 2019/3/18 10:04
 * @Description:
 */
public interface IBaseMapper<T extends BasicEntity> {
	/**
	 * @Title insert
	 * @Description 增加一个对象
	 * @param entityInfo
	 * @return
	 */
	int insert(T entityInfo);

	/**
	 * @Title insertBatch
	 * @Description 批量增加
	 * @param list
	 * @return
	 */
	int insertBatch(List<T> list);

	/**
	 * @Title delete
	 * @Description 删除数据
	 * @param entityInfo
	 */
	void delete(T entityInfo);

	/**
	 * @Title deleteBatch
	 * @Description 批量删除
	 * @param paramMap
	 */
	void deleteBatch(Map<String, Object> paramMap);

	/**
	 * @Title update
	 * @Description 修改数据
	 * @param entityInfo
	 * @return
	 */
	int update(T entityInfo);

	/**
	 * @Title selectPageCount
	 * @Description 获取记录Count数量
	 * @param paramMap
	 * @return
	 */
	Integer selectPageCount(Map<String, Object> paramMap);

	/**
	 * @Title selectPageEntities
	 * @Description 获取List<T>分页对象
	 * @param paramMap
	 * @return
	 */
	List<T> selectPageEntities(Map<String, Object> paramMap);

	/**
	 * @Title selectEntity
	 * @Description 获取T对象
	 * @param paramMap
	 * @return
	 */
	T selectEntity(Map<String, Object> paramMap);

	/**
	 * @Title selectEntities
	 * @Description 获取List<T>对象
	 * @param paramMap
	 * @return
	 */
	List<T> selectEntities(Map<String, Object> paramMap);

	/**
	 * @Title selectMap
	 * @Description 获取List<T>对象
	 * @param paramMap
	 * @return
	 */
	List<T> selectMap(Map<String, Object> paramMap);

	/**
	 * @Title invalid
	 * @Description 对象作废
	 * @param entityInfo
	 * @return
	 */
	int invalid(T entityInfo);

}
