package com.pickban.config.security.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.pickban.common.error.ErrorCode;
import com.pickban.common.error.exception.AuthorizationFailedException;
import com.pickban.common.error.exception.UserNotVerifiedException;
import com.pickban.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public UserAccessDeniedHandler(@Qualifier("handlerExceptionResolver")
                                       HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        RuntimeException exception;

        if (!user.isVerified()) {
            exception = new UserNotVerifiedException(ErrorCode.NOT_VERIFIED_USER);
        } else {
            exception = new AuthorizationFailedException(ErrorCode.AUTHORIZATION_FAILED);
        }

        handlerExceptionResolver.resolveException(request, response, null, exception);
    }
}
