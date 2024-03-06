package com.example.login.loginexam.domain.dto;

public record CommonResponse<T>(
        Integer code
        , String message
        , T data
) {
}
