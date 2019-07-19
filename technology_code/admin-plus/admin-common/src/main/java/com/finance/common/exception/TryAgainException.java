package com.finance.common.exception;

import com.finance.common.constant.ApiResultEnum;

/**
 * @author Administrator
 * 更新重试异常
 */
public class TryAgainException extends ApiException {

    public TryAgainException(ApiResultEnum apiResultEnum) {
        super(apiResultEnum);
    }

}
