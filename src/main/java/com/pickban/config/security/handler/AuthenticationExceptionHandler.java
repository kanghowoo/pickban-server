package com.pickban.config.security.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pickban.common.error.BaseExceptionHandler;
import com.pickban.common.error.ErrorCode;
import com.pickban.common.error.ErrorResponse;

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

        /*
         * Spring security에서 @PreAuthorized 어노테이션을 통과하지 못하면,
         * AccessDeniedException이 발생한다. spring security의 java config에서
         * hasAuthorized 메소드로 접근권한을 설정하면 AccessDeniedHandler 구현체를 통해
         * AccessDeniedException을 핸들링할 수 있지만, @PreAuthorized 어노테이션을 통해서는
         * AccessDeniedHandler 구현체로 핸들링이 불가능하다. 이를 극복하기 위해,
         * GlobalExceptionHandler에서 AccessDeniedException을 처리하는 로직에서 다시
         * 예외를 밖으로 던짐으로써 AccessDeniedHandler 구현체로 핸들링 할 수 있도록 처리한다.
         */
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
