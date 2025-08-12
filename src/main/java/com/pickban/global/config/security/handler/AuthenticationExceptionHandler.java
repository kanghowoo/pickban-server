package com.pickban.global.config.security.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pickban.global.error.BaseExceptionHandler;
import com.pickban.global.error.ErrorCode;
import com.pickban.global.error.ErrorResponse;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class AuthenticationExceptionHandler extends BaseExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException e) {
        log.info(e.getMessage(), e);
        return responseAsCode(ErrorCode.LOGIN_AUTHENTICATION_FAILED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e)
            throws AccessDeniedException {
        throw e;
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleInsufficientAuthenticationException(
            InsufficientAuthenticationException e) {
        log.info(e.getMessage(), e);
        return responseAsCode(ErrorCode.NEED_AUTHENTICATION);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        log.info(e.getMessage(), e);
        return responseAsCode(ErrorCode.TOKEN_EXPIRED);
    }
}
