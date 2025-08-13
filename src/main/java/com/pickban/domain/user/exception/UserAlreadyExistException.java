package com.pickban.domain.user.exception;

import com.pickban.global.error.ErrorCode;
import com.pickban.global.error.exception.BusinessException;

public class UserAlreadyExistException extends BusinessException {
    public UserAlreadyExistException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }

}
