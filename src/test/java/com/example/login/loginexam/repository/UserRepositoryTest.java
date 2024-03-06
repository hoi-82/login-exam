package com.example.login.loginexam.repository;

import com.example.login.loginexam.domain.dto.RegisterUserDto;
import com.example.login.loginexam.domain.entity.User;
import com.example.login.loginexam.domain.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("사용자_등록_테스트")
    void 사용자_등록_테스트() throws Exception {
        // Arrange
        RegisterUserDto userDto = new RegisterUserDto(
                "test@gmail.com"
                , "password!@#123"
                , "dragon"
                , Role.USER
                , true
                , true
        );

        User user = User.dtoToEntity(userDto);

        // Act
        userRepository.save(user);

        // Assert
        Assertions.assertNotNull(user.getId());
    }

    @Test
    @DisplayName("사용자_조회")
    void 사용자_조회() throws Exception {
        // Arrange
        RegisterUserDto userDto = new RegisterUserDto(
                "test@gmail.com"
                , "password!@#123"
                , "dragon"
                , Role.USER
                , true
                , true
        );

        User user = User.dtoToEntity(userDto);

        // Act
        userRepository.save(user);

        User findUser = userRepository.findByEmailAddress("test@gmail.com")
                .orElse(null);

        // Assert
        assertEquals(user, findUser);
    }
}