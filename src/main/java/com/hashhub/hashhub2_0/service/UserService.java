package com.hashhub.hashhub2_0.service;

import com.hashhub.hashhub2_0.dto.RegistrationDto;
import com.hashhub.hashhub2_0.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}


/***************************************************************************************
 *    Title: RunGroop.java
 *    Author: teddysmithdev
 *    Date: 12/20/2022
 *    Code version: 1.0
 *    Availability: https://github.com/teddysmithdev/RunGroop-Java
 *    Accessed: 01/02/2024
 *
 ***************************************************************************************/