package com.humanup.matrix.management.api.gatway.aop.dto;

public class AuthorizationException extends HttpException {
    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Authorization";
    }
}