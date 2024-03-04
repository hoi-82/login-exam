package com.example.login.loginexam.domain.dto;

import com.example.login.loginexam.domain.enums.Role;
import com.example.login.loginexam.domain.enums.annotation.ValidEnum;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterUserDto(
        @Email(message = "이메일 형식과 맞지 않습니다.")
        String emailAddress,

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}"
                , message = "비밀번호는 영문자, 숫자, 특수문자를 포함한 8~20자로 입력해야 합니다.")
        String password,

        @NotBlank(message = "사용자 명이 입력되지 않았습니다.")
        String userName,

        @ValidEnum(message = "잘못된 권한이 입력되었습니다.", enumClass = Role.class)
        Role role,

        @AssertTrue(message = "이용약관을 동의해야 합니다.")
        boolean tos, // 필수값..이용약관 동의 (Terms of Service)

        @AssertTrue(message = "개인정보 수집 및 이용을 동의해야 합니다.")
        boolean pic // 필수값..개인정보 수집 동의 (Personal Information Collection and Useage Agreement)
        ) {
}
