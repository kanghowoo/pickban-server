package com.pickban.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserVerifyResponse {
    private final String email;
}
