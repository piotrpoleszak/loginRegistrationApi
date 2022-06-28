package com.piotrpoleszak.loginAndRegistration.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationRepository confirmationRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
