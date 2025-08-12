package com.pickban.domain.auth.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final long id;
    private final String nickname;
    private final String email;

    public LoginResponse(long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}
