package com.pickban.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class MailTemplateService {

    public String getVerifyEmailTemplate() throws IOException {
        Resource resource = new ClassPathResource("templates/verification-mail.html");
        Path path = resource.getFile().toPath();

        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public String getPasswordResetEmailTemplate() throws IOException {
        Resource resource = new ClassPathResource("templates/password-reset-mail.html");
        Path path = resource.getFile().toPath();

        return Files.readString(path, StandardCharsets.UTF_8);
    }

}
