package com.piotrpoleszak.loginAndRegistration.user;

import com.piotrpoleszak.loginAndRegistration.exception.CoreException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.piotrpoleszak.loginAndRegistration.exception.ErrorCode.NOT_FOUND;
import static com.piotrpoleszak.loginAndRegistration.exception.ErrorSubcode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CoreException(NOT_FOUND, USER_NOT_FOUND));
    }
}
