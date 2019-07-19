/**
 * @Title ApiLogMapper.java
 * @Package com.shein.tss.apiLog.mapper
 * @Description 接口日志表
 * @author weipeng
 * @date 2019-01-15 09:18:19
 * @version : V1.0
 */

package com.example.demo.dao.user.mapper;

import com.example.demo.config.IBaseMapper;
import com.example.demo.dao.user.model.UserEntity;


/**
 * @ClassName UserMapper
 * @author weipeng
 * @date 2019-01-15 09:18:19
 */
public interface UserMapper extends IBaseMapper<UserEntity> {

    int insertUser(UserEntity userEntity);

}
