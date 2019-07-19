package com.my.project.exception;

import lombok.Data;

/**
 * @Author: weixiaotao
 * @ClassName BizException
 * @Date: 2018/12/14 15:49
 * @Description:
 */
@Data
public class BizException extends RuntimeException {
	private static final long serialVersionUID = -1375871034002553203L;

	private String message;

	public BizException(){
		super();
	}

	public BizException(String message) {
		super(message);
		this.message = message;
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
}
