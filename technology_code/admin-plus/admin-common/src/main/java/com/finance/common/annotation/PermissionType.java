package com.finance.common.annotation;

/**
 * 权限枚举
 */
public enum PermissionType {
    /**
     * 添加权限
     */
    ADD("add"),
    /**
     * 编辑权限
     */
    EDIT("edit"),
    /**
     * 查看权限
     */
    QUERY("query"),
    /**
     * 删除权限
     */
    DEL("del");

    private String type;
    
    PermissionType(String type){
        this.type=type;
    }
    public String getType() {
        return type;
    }
}
