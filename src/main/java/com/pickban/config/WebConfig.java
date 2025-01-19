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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200", "http://localhost:8080",
                                "https://d2x9irjf1wenrr.cloudfront.net",
                                "https://api.mybanpick.kr")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true);
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
