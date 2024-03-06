package com.example.login.loginexam.controller;

import com.example.login.loginexam.domain.dto.CommonResponse;
import com.example.login.loginexam.domain.dto.LoginDto;
import com.example.login.loginexam.domain.dto.RegisterUserDto;
import com.example.login.loginexam.domain.dto.Token;
import com.example.login.loginexam.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/sign")
@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping("/test")
    public ResponseEntity<CommonResponse<String>> test() throws Exception {
        return ResponseEntity.ok(new CommonResponse<>(200, "test", null));
    }

    @PostMapping("/up")
    public ResponseEntity<CommonResponse<String>> signup(RegisterUserDto userDto) throws Exception {
        signService.signupUser(userDto);
        return ResponseEntity.ok(new CommonResponse<>(200, "계정이 성공적으로 등록되었습니다.", "ok"));
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<Token>> login(LoginDto loginDto) throws Exception {
        return ResponseEntity.ok(new CommonResponse<>(200, "성공적으로 로그인되었습니다.", signService.login(loginDto)));
    }
}
