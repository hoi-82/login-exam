package com.example.login.loginexam.controller;

import com.example.login.loginexam.domain.dto.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("login")
@RestController
public class LoginController {

    @PostMapping("/test")
    public ResponseEntity<CommonResponse> test() throws Exception {
        return ResponseEntity.ok(new CommonResponse(200, "test"));
    }
}
