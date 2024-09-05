package com.example.SpringBootEmail.services;

import com.example.SpringBootEmail.models.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    //    @Autowired
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(EmailRequest emailRequest) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(emailRequest.getSendEmailTo());
        simpleMailMessage.setSubject(emailRequest.getSubject());
        simpleMailMessage.setText(emailRequest.getBody());

        javaMailSender.send(simpleMailMessage);
    }

    public void sendThymeLeafEmail(EmailRequest emailRequest) {
        Context context = new Context();
        HashMap<String, Object> contextData = new HashMap<>();
        contextData.put("sendEmailTo", emailRequest.getSendEmailTo());
        contextData.put("subject", emailRequest.getSubject());
        contextData.put("body", emailRequest.getBody());
        contextData.put("recepientName", emailRequest.getRecepientName());
        contextData.put("senderName", emailRequest.getSenderName());


        context.setVariables(contextData);
        String html = templateEngine.process("emailTemplate", context);
        System.out.println(html);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(emailRequest.getSendEmailTo());
            mimeMessageHelper.setSubject(emailRequest.getSubject());
            mimeMessageHelper.setText(html,true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
