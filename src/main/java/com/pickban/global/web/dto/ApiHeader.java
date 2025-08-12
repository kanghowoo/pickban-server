package com.pickban.global.web.dto;

import lombok.Getter;

@Getter
public enum ApiHeader {
    /* auth headers */
    AUTH_TOKEN("Authorization"),
    REFRESH_TOKEN("refreshToken");

    private final String name;

    ApiHeader(String name) {
        this.name = name;
    }
}
