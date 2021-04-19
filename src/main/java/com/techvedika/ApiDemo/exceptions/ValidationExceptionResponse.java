package com.techvedika.ApiDemo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationExceptionResponse {
    private Date timeStamp;
    private Map<String, String> message;
    private String detail;
}
