package com.pickban.common.error.exception;

import com.pickban.common.error.ErrorCode;

public class EntityBadStatusException extends BusinessException {
    public EntityBadStatusException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EntityBadStatusException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
