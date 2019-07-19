package com.finance.common.constant;

/**
 * @author weixiaotao
 */
public enum ApiResultEnum {
	/**
	 * http 返回
	 */
	SUCCESS("200","ok"),
	FAILED("400","请求失败"),
	ERROR("500","不知名错误"),
	ERROR_NULL("501","空指针异常"),
	ERROR_CLASS_CAST("502","类型转换异常"),
	ERROR_RUNTIME("503","运行时异常"),
	ERROR_IO("504","上传文件异常"),
	ERROR_METHOD_NOT_SUPPORT("505","请求方法错误"),

	/**
	 * 参数
	 */
	PARAMETER_NULL("10001","缺少参数或值为空"),

	/**
	 * 账户
	 */
	ACCOUNT_LOCK("20001","账号已锁定"),
	ACCOUNT_NOT_FOUND("20002","找不到账户信息"),
	ACCOUNT_PASSWORD_ERROR("20003","用户名密码错误"),
	ACCOUNT_EXIST("20004","账号已存在"),

	/**
	 * 权限
	 */
	AUTH_NOT_HAVE("30001","没有权限"),

	/**
	 * 文件相关
	 */
	FILE_IS_NULL("40001","文件为空"),

	/**
	 * 业务相关 待补充
	 */
	;

	private String status;

	private String message;



	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}


	ApiResultEnum(String status,String message) {
		this.message = message;
		this.status = status;
	}

	
}
