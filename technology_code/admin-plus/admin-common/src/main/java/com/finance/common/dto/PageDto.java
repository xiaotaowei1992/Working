package com.finance.common.dto;

import lombok.Data;

/**
 * @author Administrator
 * 分页查询相关
 */
@Data
public class PageDto {

    /**
     * 当前页
     */
     public Integer pageNo;
    /**
     * 每页显示的大小
     */
    public Integer pageSize;

    /**
     * 关键字
     */
    private String keyword;
}
