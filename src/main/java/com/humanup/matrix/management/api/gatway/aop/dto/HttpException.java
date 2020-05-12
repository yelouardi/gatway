package com.humanup.matrix.management.api.gatway.aop.dto;

public class HttpException extends Exception {

    public HttpException(String message) {
        super(message);
    }

    public HttpException() {
        super();
    }
}
