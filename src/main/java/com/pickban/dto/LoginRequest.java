package com.pickban.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {
    @NotEmpty
    private final String email;

    @NotEmpty
    private final String password;
}
