package com.humanup.matrix.management.api.gatway.aop.dto;

public class RoleException extends HttpException {
    public RoleException(String message) {
        super(message);
    }

    public RoleException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Role";
    }
}
