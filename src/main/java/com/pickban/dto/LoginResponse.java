package com.pickban.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final long id;
    private final String nickname;

    public LoginResponse(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
