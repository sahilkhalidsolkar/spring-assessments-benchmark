package com.example.SpringBootEmail.controllers;

import com.example.SpringBootEmail.models.EmailRequest;
import com.example.SpringBootEmail.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-mail")
    public String sendMail(@Valid @RequestBody EmailRequest emailRequest){

        emailService.sendEmail(emailRequest);
        return "send successfully";
    }
    @PostMapping("/send-thym-mail")
    public String sendThymMail(@Valid @RequestBody EmailRequest emailRequest){

        emailService.sendThymeLeafEmail(emailRequest);
        return "send thym successfully";
    }

}
