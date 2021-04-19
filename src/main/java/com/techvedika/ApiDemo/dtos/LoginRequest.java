package com.techvedika.ApiDemo.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty(message = "user name is required")
    private String userName;

    @NotEmpty(message = "password is required")
    private String password;
}
