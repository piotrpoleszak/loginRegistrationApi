package com.piotrpoleszak.loginAndRegistration.registration;

import com.piotrpoleszak.loginAndRegistration.exception.CoreException;
import com.piotrpoleszak.loginAndRegistration.registration.token.ConfirmationToken;
import com.piotrpoleszak.loginAndRegistration.registration.token.ConfirmationTokenService;
import com.piotrpoleszak.loginAndRegistration.user.UserDto;
import com.piotrpoleszak.loginAndRegistration.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static com.piotrpoleszak.loginAndRegistration.exception.ErrorCode.VALIDATE_ERROR;
import static com.piotrpoleszak.loginAndRegistration.exception.ErrorSubcode.EMAIL_NOT_VALID;
import static com.piotrpoleszak.loginAndRegistration.user.UserRole.USER;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        isEmailValidate(request);

        return userService.signUpUser(
                new UserDto(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        USER
                )
        );
    }

    private void isEmailValidate(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new CoreException(VALIDATE_ERROR, EMAIL_NOT_VALID);
        }
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
