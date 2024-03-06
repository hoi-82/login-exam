package com.example.login.loginexam.component;

import com.example.login.loginexam.domain.entity.hash.RefreshToken;
import com.example.login.loginexam.domain.vo.JwtConfigurationKey;
import com.example.login.loginexam.repository.JwtCachingRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class JwtTokenProvider implements InitializingBean {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtCachingRepository jwtCachingRepository;
    private final JwtConfigurationKey jwtConfigurationKey;

    private static SecretKey signingKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] secretKeyBytes = Decoders.BASE64.decode(jwtConfigurationKey.getSecretKey());
        signingKey = Keys.hmacShaKeyFor(secretKeyBytes);
    }

    public String createAccessToken(Authentication authentication) {
        return createToken(authentication, jwtConfigurationKey.getAccessTokenExpirationTime());
    }

    @Transactional
    public String createRefreshToken(Authentication authentication) {
        String refreshToken = createToken(authentication, jwtConfigurationKey.getRefreshTokenExpirationTime());
        RefreshToken refreshTokenHash = RefreshToken.builder()
                .id(authentication.getName())
                .token(refreshToken)
                .expirationInSeconds(jwtConfigurationKey.getRefreshTokenExpirationTime())
                .build();

        jwtCachingRepository.save(refreshTokenHash);

        return refreshToken;

    }

    // 토큰 분석 후 정보 추출
    public Authentication getAuthentication(String token) {
        String emailAddress = Jwts.parser().verifyWith(signingKey).build()
                .parseSignedClaims(token).getPayload().getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(emailAddress);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(token);

            return true;
        } catch(ExpiredJwtException e) {
            log.warn("[jwt token is expired] token -> {}", token);
        } catch(JwtException e) {
            log.error("[jwt token error] token -> {}", token);
        }

        return false;
    }

    private String createToken(Authentication authentication, Long expirationTime) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + expirationTime);

        return Jwts.builder()
                .claim(Claims.SUBJECT, authentication.getName())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(signingKey)
                .compact();
    }
}
