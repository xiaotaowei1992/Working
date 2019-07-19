package com.finance.core.admin.service.impl;

import com.finance.core.admin.service.IUserRoleService;
import com.finance.core.admin.entity.Role;
import com.finance.core.admin.entity.UserRole;
import com.finance.core.admin.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rstyro
 * @since 2018-12-14
 */
@Transactional
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getUserRoles(Integer userId) throws Exception {
        return userRoleMapper.getUserRoles(userId);
    }
}
