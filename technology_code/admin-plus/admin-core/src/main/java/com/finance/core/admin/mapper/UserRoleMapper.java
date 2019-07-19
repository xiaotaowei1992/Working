package com.finance.core.admin.mapper;

import com.finance.core.admin.entity.UserRole;
import com.finance.core.admin.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rstyro
 * @since 2018-12-14
 */
@Component
public interface UserRoleMapper extends BaseMapper<UserRole> {
      List<Role> getUserRoles(Integer userId);
}
