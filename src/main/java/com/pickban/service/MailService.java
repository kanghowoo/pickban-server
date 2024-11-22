package com.pickban.service;

import com.pickban.entity.User;

public interface MailService {
    void sendVerifyEmail(User user, String token);
    void sendPasswordResetEmail(User user, String token);

}
