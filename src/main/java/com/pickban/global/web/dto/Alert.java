package com.pickban.global.web.dto;

import lombok.Getter;

@Getter
public class Alert {
    private final String text;

    private Alert(String text) {
        this.text = text;
    }

    public static Alert of(String text) {
        return new Alert(text);
    }

}
