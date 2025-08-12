package com.pickban.domain.verification.domain.model;

import lombok.Getter;

@Getter
public enum VerifyStatus {
    IN_PROGRESS(0, "진행중"),
    VERIFIED(1, "인증완료");

    private final int key;
    private final String title;

    VerifyStatus(int key, String title) {
        this.key = key;
        this.title = title;
    }
}
