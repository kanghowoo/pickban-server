package com.pickban.common.error;

import org.springframework.http.ResponseEntity;

import com.pickban.common.response.Alert;

public abstract class BaseExceptionHandler {
    protected ResponseEntity<ErrorResponse> responseAsCode(ErrorCode code) {
        ErrorResponse response = ErrorResponse.of(code);
        return new ResponseEntity<>(response, code.getStatus());
    }

    protected ResponseEntity<ErrorResponse> responseAsCode(ErrorCode code, Alert alert) {
        ErrorResponse response = ErrorResponse.of(code, alert);
        return new ResponseEntity<>(response, code.getStatus());
    }

}
