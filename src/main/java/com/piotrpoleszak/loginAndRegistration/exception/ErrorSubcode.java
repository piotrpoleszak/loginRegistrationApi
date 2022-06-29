package com.piotrpoleszak.loginAndRegistration.exception;

public enum ErrorSubcode {
    USER_NOT_FOUND,
    EMAIL_NOT_VALID,
    USER_WITH_EMAIL_ALREADY_EXIST,
    EMAIL_ALREADY_CONFIRMED,
    TOKEN_EXPIRED,
    TOKEN_NOT_FOUND,
    EMAIL_SEND_FAILED
}
