package com.example.demo.config;

/**
 * @Author: weixiaotao
 * @ClassName AdusResponse
 * @Date: 2019/3/14 10:32
 * @Description:
 */
public class AdusResponse {

    /**
     * 记录行为的结果，默认0表示成功，-1表示失败
     */
    private String code;

    /**
     * 记录错误的行为信息
     */
    private String msg;

    /**
     * 记录行为获取的数据
     */
    private Object data;

    /**
     * 构造方法，自定义返回值
     *
     * @param code 状态码
     * @param msg  错误信息
     * @param data 数据
     */
    public AdusResponse(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}