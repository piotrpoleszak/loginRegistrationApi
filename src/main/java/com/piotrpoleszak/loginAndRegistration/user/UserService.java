package com.piotrpoleszak.loginAndRegistration.user;

import com.piotrpoleszak.loginAndRegistration.exception.CoreException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.piotrpoleszak.loginAndRegistration.exception.ErrorCode.NOT_FOUND;
import static com.piotrpoleszak.loginAndRegistration.exception.ErrorCode.VALIDATE_ERROR;
import static com.piotrpoleszak.loginAndRegistration.exception.ErrorSubcode.USER_WITH_EMAIL_ALREADY_EXIST;
import static com.piotrpoleszak.loginAndRegistration.exception.ErrorSubcode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CoreException(NOT_FOUND, USER_NOT_FOUND));
    }

    public String signUpUser(UserDto userDto) {
        var userExist = userRepository.findByEmail(userDto.email()).isPresent();

        if (userExist) {
            throw new CoreException(VALIDATE_ERROR, USER_WITH_EMAIL_ALREADY_EXIST);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(userDto.password());

        var newUser = new User(userDto.firstName(),
                userDto.lastName(), userDto.email(), encodedPassword, userDto.role());

        userRepository.save(newUser);

        return "it works";
    }
}
