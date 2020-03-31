package com.example.demo.config;

import org.springframework.http.HttpStatus;

/*
* 自定义权限异常
* */
public class AuthorityException extends RuntimeException {
    public AuthorityException(String message) {
        super(message);
    }

    public AuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_ACCEPTABLE; //406 权限不足
    }

}
