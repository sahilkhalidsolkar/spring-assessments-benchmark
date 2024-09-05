package com.example.SpringBootEmail.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailRequest {
    @NotNull
    private String sendEmailTo;
    @NotNull
    private String subject;
    @NotNull
    private String body;
    @NotNull
    private String recepientName;
    @NotNull
    private String senderName;

}
