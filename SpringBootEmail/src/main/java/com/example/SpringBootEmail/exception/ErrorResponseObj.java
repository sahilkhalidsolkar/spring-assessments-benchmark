package com.example.SpringBootEmail.exception;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorResponseObj {
    private Map<String,String> errors;
   private Integer status;
}
