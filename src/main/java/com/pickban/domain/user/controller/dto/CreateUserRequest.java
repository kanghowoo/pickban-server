package com.pickban.domain.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {

    @NotBlank
    @Size(min = 2, max = 10)
    @JsonProperty("nickname")
    private final String nickname;

    @NotBlank
    @Email
    @JsonProperty("email")
    private final String email;

    @NotBlank
    @Size(min = 6)
    @JsonProperty("rawPassword")
    private final String rawPassword;

    @JsonCreator
    public CreateUserRequest(@JsonProperty("nickname") String nickname,
                             @JsonProperty("email") String email,
                             @JsonProperty("rawPassword") String rawPassword) {
        this.nickname = nickname;
        this.email = email;
        this.rawPassword = rawPassword;
    }
}
