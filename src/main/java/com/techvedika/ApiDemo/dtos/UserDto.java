package com.techvedika.ApiDemo.dtos;

import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {


    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "email is required")
    @Email(message = "Should be a valid email")
    private String emailId;

    @NotEmpty(message = "password is required")
    private String password;

    private Long mobileNo;

    private String city;
}
