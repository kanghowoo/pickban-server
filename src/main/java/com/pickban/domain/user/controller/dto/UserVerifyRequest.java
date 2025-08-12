package com.pickban.domain.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserVerifyRequest {

    @NotEmpty
    @JsonProperty("email")
    private final String email;

    @NotEmpty
    @JsonProperty("token")
    private final String token;

    @JsonCreator
    public UserVerifyRequest(@JsonProperty("email") String email,
                             @JsonProperty("token") String token) {
        this.email = email;
        this.token = token;
    }
}
