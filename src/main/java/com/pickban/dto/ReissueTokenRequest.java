package com.pickban.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReissueTokenRequest {

    @NotBlank
    private final String token;

    @NotBlank
    private final String refreshToken;
}
