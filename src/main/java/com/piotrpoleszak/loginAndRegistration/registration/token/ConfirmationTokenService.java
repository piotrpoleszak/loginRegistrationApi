package com.piotrpoleszak.loginAndRegistration.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationRepository confirmationRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationRepository.save(token);
    }
}
