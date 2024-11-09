package com.pickban.common.error;

import org.springframework.http.ResponseEntity;

public abstract class BaseExceptionHandler {
    protected ResponseEntity<ErrorResponse> responseAsCode(ErrorCode code) {
        ErrorResponse response = ErrorResponse.of(code);
        return new ResponseEntity<>(response, code.getStatus());
    }

}
