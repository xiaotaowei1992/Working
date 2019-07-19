package com.finance.core.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.constant.Result;
import com.finance.core.admin.dto.LoginDTO;
import com.finance.core.admin.entity.Login;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rstyro
 * @since 2018-12-14
 */
public interface ILoginService extends IService<Login> {
    public Result login(LoginDTO dto, HttpSession session) throws Exception;
    public String logout(HttpSession session) throws Exception;
}
