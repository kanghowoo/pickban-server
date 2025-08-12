package com.pickban.domain.mail.application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
public class MailTemplateService {

    public String getVerifyEmailTemplate() throws IOException {
        Resource resource = new ClassPathResource("templates/verification-mail.html");
        try (InputStream inputStream = resource.getInputStream()) {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        }
    }

    public String getPasswordResetEmailTemplate() throws IOException {
        Resource resource = new ClassPathResource("templates/password-reset-mail.html");
        try (InputStream inputStream = resource.getInputStream()) {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        }
    }

}
