package com.pickban.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pickban.config.filter.ExceptionHandlerFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public WebConfig(
            @Lazy
            @Qualifier("handlerExceptionResolver")
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }


    @Bean
    public FilterRegistrationBean<ExceptionHandlerFilter> exceptionHandlerFilter() {
        ExceptionHandlerFilter filter = new ExceptionHandlerFilter(handlerExceptionResolver);
        FilterRegistrationBean<ExceptionHandlerFilter> registrationBean =
                new FilterRegistrationBean<>(filter);
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
