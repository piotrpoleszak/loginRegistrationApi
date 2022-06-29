package com.piotrpoleszak.loginAndRegistration.email;

public interface EmailSender {
    void send(String to, String email);
}
