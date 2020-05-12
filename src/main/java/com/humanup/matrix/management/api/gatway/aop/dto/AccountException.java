package com.humanup.matrix.management.api.gatway.aop.dto;

public class AccountException extends HttpException {
    public AccountException(String message) {
        super(message);
    }

    public AccountException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Account";
    }
}
