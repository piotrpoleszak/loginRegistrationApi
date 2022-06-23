package com.piotrpoleszak.loginAndRegistration.exception;

public enum ErrorCode {
    NOT_FOUND(404);

    private final int httpStatus;

    ErrorCode(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
