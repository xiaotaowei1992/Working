package com.finance.core.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.constant.Result;
import com.finance.core.admin.dto.UserDTO;
import com.finance.core.admin.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rstyro
 * @since 2018-12-14
 */
public interface IUserService extends IService<User> {
    public List<User> getUserList() throws  Exception;
    public Result getRole(String userId) throws  Exception;
    public Result add(User user) throws  Exception;
    public Result edit(User user) throws  Exception;
    public Result editRole(UserDTO dto) throws  Exception;
    public Result del(String userId) throws  Exception;
    public User getUserInfo(User user) throws  Exception;
}
