package com.pickban.domain.mail.infrastructure.external;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pickban.domain.mail.application.MailService;
import com.pickban.domain.mail.application.MailTemplateService;
import com.pickban.domain.user.domain.model.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GoogleMailService implements MailService {

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${verify-url}")
    private String verifyUrl;

    private static final String VERIFICATION_MAIL_TITLE= "My BanPick 인증 메일입니다.";
    private static final String PASSWORD_RESET_MAIL_TITLE = "My BanPick 비밀번호 재설정 안내 메일입니다.";

    private final JavaMailSender javaMailSender;
    private final MailTemplateService mailTemplateService;

    public GoogleMailService(JavaMailSender javaMailSender, MailTemplateService mailTemplateService) {
        this.javaMailSender = javaMailSender;
        this.mailTemplateService = mailTemplateService;
    }

    @Override
    public void sendVerifyEmail(User user, String token) {
        createVerifyMimeMessage(user, token);
    }

    @Override
    public void sendPasswordResetEmail(User user, String token) {
        createPasswordResetMimeMessage(user, token);
    }

    private void createVerifyMimeMessage(User user, String token) {
        MimeMessage message = javaMailSender.createMimeMessage();
        final String email = user.getEmail();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(VERIFICATION_MAIL_TITLE);

            final String verifyUrl = getVerifyUrl(email, token);
            final String htmlTemplate = mailTemplateService.getVerifyEmailTemplate();

            helper.setText(setMailContent(verifyUrl, htmlTemplate), true);
            javaMailSender.send(message);
        } catch (MessagingException | IOException e) {
            log.error(e.toString());
        }
    }

    private void createPasswordResetMimeMessage(User user, String token) {
        MimeMessage message = javaMailSender.createMimeMessage();
        final String email = user.getEmail();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(PASSWORD_RESET_MAIL_TITLE);

            final String verifyUrl = getVerifyUrl(email, token);
            final String htmlTemplate = mailTemplateService.getPasswordResetEmailTemplate();

            helper.setText(setMailContent(verifyUrl, htmlTemplate), true);
            javaMailSender.send(message);
        } catch (MessagingException | IOException e) {
            log.error(e.toString());
        }
    }

    private String setMailContent(String verifyUrl, String htmlTemplate) {
        Document doc = Jsoup.parse(htmlTemplate);

        Element urlElement = doc.getElementById("authUrl");
        Element urlTextElement = doc.getElementById("authUrlText");

        if (urlElement != null && urlTextElement != null) {
            urlElement.attr("href", verifyUrl);
            urlTextElement.appendText(verifyUrl);
        }

        return doc.html();
    }

    private String getVerifyUrl(String email, String token) {
        log.info("set url : {}",
                 String.format(verifyUrl, email, token));
        return String.format(verifyUrl, email, token);

    }

}
