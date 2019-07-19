package com.finance.core.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.constant.Result;
import com.finance.core.admin.dto.QxDTO;
import com.finance.core.admin.entity.Role;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rstyro
 * @since 2018-12-14
 */
public interface IRoleService extends IService<Role> {

    public List<Role> getRolelist() throws  Exception;
    public Result getMenu(QxDTO dto) throws  Exception;
    public Result edit(Role role) throws  Exception;
    public Result add(Role role, HttpSession session) throws  Exception;
    public Result del(Integer roleId) throws  Exception;

}
