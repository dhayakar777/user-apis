package com.techvedika.ApiDemo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private Date timeStamp;

    private String message;

    private List<String> details;
}
