package com.pickban.domain.mail.application;

import com.pickban.domain.user.domain.model.User;

public interface MailService {
    void sendVerifyEmail(User user, String token);
    void sendPasswordResetEmail(User user, String token);

}
