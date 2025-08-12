package com.pickban.global.error;

import org.springframework.http.ResponseEntity;

import com.pickban.global.web.dto.Alert;

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
