package com.j3s.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException(String errorMessage){
        super(errorMessage);
    }
}

