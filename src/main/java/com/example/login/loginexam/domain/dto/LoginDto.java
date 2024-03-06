package com.example.login.loginexam.domain.dto;

import jakarta.validation.constraints.Email;

public record LoginDto(
        @Email
        String emailAddress
        , String password
) {
}
