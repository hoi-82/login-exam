package com.example.login.loginexam.service;

import com.example.login.loginexam.component.JwtTokenProvider;
import com.example.login.loginexam.domain.dto.LoginDto;
import com.example.login.loginexam.domain.dto.RegisterUserDto;
import com.example.login.loginexam.domain.dto.Token;
import com.example.login.loginexam.domain.entity.User;
import com.example.login.loginexam.domain.enums.Role;
import com.example.login.loginexam.domain.enums.exception.ServerExceptionStatus;
import com.example.login.loginexam.exception.BusinessException;
import com.example.login.loginexam.repository.JwtCachingRepository;
import com.example.login.loginexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignService {
    private final JwtCachingRepository jwtCachingRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public void signupUser(RegisterUserDto userDto) {
        if(!userDto.role().equals(Role.USER))
            throw new BusinessException(ServerExceptionStatus.SIGNUP_ERROR);
        else if(userRepository.findByEmailAddress(userDto.emailAddress()).isPresent())
            throw new BusinessException(ServerExceptionStatus.SIGNUP_EXIST_EMAIL_ERROR);

        try {
            User user = User.dtoToEntity(userDto);

            userRepository.save(user);
        } catch(Exception e) {
            throw new BusinessException(ServerExceptionStatus.SIGNUP_ERROR);
        }
    }

    @Transactional
    public void signup(RegisterUserDto userDto) {
        if(userRepository.findByEmailAddress(userDto.emailAddress()).isPresent())
            throw new BusinessException(ServerExceptionStatus.SIGNUP_EXIST_EMAIL_ERROR);

        try {
            User user = User.dtoToEntity(userDto);

            userRepository.save(user);
        } catch(Exception e) {
            throw new BusinessException(ServerExceptionStatus.SIGNUP_ERROR);
        }
    }

    public Token login(LoginDto loginDto) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.emailAddress(), loginDto.password());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        if(jwtCachingRepository.existsById(loginDto.emailAddress())) jwtCachingRepository.deleteById(loginDto.emailAddress());

        String accessToken = jwtTokenProvider.createAccessToken(authentication);
        String refreshToken = jwtTokenProvider.createRefreshToken(authentication);

        return new Token(accessToken, refreshToken);
    }
}
