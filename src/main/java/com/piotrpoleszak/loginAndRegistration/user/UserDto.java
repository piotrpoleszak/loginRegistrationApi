package com.piotrpoleszak.loginAndRegistration.user;

public record UserDto(String firstName,
                      String lastName,
                      String email,
                      String password,
                      UserRole role) {
}
