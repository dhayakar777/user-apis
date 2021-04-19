package com.techvedika.ApiDemo.exceptions;

public class InvalidUserNameOrPasswordException extends RuntimeException {

    public InvalidUserNameOrPasswordException(String s) {
        super(s);
    }
}
