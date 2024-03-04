package com.example.login.loginexam.service;

import com.example.login.loginexam.repository.JwtCachingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtCachingService {
    private final JwtCachingRepository jwtCachingRepository;

}
