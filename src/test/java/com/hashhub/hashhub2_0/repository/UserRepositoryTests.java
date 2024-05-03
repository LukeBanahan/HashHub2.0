package com.hashhub.hashhub2_0.repository;

import com.hashhub.hashhub2_0.models.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @Author Luke_Banahan
 * @Version 2.0
 * @Date 01/05/2024
 * @Package com.hashhub.hashhub2_0.repository
 * @Project HashHub2.0
 */

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test //Test that findByUsername method works.
    public void UserRepository_FindByUser_ReturnUser() {
        //Arrange
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username("testUser")
                .password("testPass")
                .email("test@test.com")
                .build();

        //Act
        userRepository.save(userEntity);
        UserEntity foundUser = userRepository.findByUsername("testUser");

        //Assert
        Assertions.assertEquals(userEntity, foundUser);
    }

    @Test //Test that findByEmail method works
    public void UserRepository_FindByEmail_ReturnUser() {
        //Arrange
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username("testUser")
                .password("testPass")
                .email("test@test.com")
                .build();

        //Act
        userRepository.save(userEntity);
        UserEntity foundUser = userRepository.findByEmail("test@test.com");

        //Assert
        Assertions.assertEquals(userEntity, foundUser);
    }
}
