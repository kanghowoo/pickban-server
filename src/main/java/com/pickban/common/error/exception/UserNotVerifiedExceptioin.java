package com.pickban.common.error.exception;

import com.pickban.common.error.ErrorCode;

public class UserNotVerifiedExceptioin extends BusinessException{
    public UserNotVerifiedExceptioin(ErrorCode errorCode) {
        super(errorCode);
    }
}
