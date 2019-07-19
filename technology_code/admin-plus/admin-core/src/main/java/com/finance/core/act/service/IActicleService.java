package com.finance.core.act.service;

import com.finance.core.act.entity.Acticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.common.constant.Result;
import com.finance.common.dto.PageDto;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *   服务类
 * </p>
 *
 * @author rstyro
 * @since 2019-3-26
 */
public interface IActicleService extends IService<Acticle> {
    public Result getList(PageDto dto) throws  Exception;
    public Result add(Acticle item, HttpSession session) throws  Exception;
    public Result edit(Acticle item, HttpSession session) throws  Exception;
    public Result del(Long id, HttpSession session) throws  Exception;
    public Result getDetail(Long id) throws  Exception;
}
