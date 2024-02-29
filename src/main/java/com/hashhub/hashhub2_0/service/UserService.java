package com.hashhub.hashhub2_0.service;

import com.hashhub.hashhub2_0.dto.RegistrationDto;
import com.hashhub.hashhub2_0.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
