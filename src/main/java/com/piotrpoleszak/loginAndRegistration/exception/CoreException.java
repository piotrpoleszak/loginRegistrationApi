package com.piotrpoleszak.loginAndRegistration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class CoreException extends RuntimeException{

    private final ErrorCode errorCode;
    private final ErrorSubcode errorSubcode;

    public CoreException(ErrorCode errorCode, ErrorSubcode errorSubcode) {
        this.errorCode = errorCode;
        this.errorSubcode = errorSubcode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public ErrorSubcode getErrorSubcode() {
        return errorSubcode;
    }

    @Override
    public String toString() {
        return "CoreException{" +
                "errorCode=" + errorCode +
                ", errorSubcode=" + errorSubcode +
                '}';
    }
}
