package com.techvedika.ApiDemo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
        List<String> exceptionDetails = new ArrayList<>();
        exceptionDetails.add(exception.getLocalizedMessage());
        ExceptionResponse response = new ExceptionResponse(new Date(), "Server Error", exceptionDetails);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ExceptionResponse response = new ExceptionResponse(new Date(), "Validation failed", details);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
