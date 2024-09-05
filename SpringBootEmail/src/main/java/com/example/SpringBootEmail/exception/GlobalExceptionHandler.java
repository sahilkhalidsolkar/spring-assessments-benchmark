package com.example.SpringBootEmail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseObj> handleValidationError(MethodArgumentNotValidException ex){
        Map<String,String> error = new HashMap<>();
        ErrorResponseObj errorResponseObj= new ErrorResponseObj();
        ex.getFieldErrors().forEach((fieldError)->{
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        errorResponseObj.setErrors(error);
        errorResponseObj.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponseObj,HttpStatus.BAD_REQUEST);
    }
}
