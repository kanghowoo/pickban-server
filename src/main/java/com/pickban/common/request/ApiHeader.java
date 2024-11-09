package com.pickban.common.request;

import lombok.Getter;

@Getter
public enum ApiHeader {
    /* auth headers */
    AUTH_TOKEN("Authorization");

    private final String name;

    ApiHeader(String name) {
        this.name = name;
    }
}
