package com.example.login.loginexam.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigurationKey {
    private String secretKey;
    private Long accessTokenExpirationTime;
    private Long refreshTokenExpirationTime;
}
