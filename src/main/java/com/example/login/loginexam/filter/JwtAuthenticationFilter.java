package com.example.login.loginexam.filter;

import com.example.login.loginexam.component.JwtTokenProvider;
import com.example.login.loginexam.domain.enums.JwtExceptionStatus;
import com.example.login.loginexam.exception.JwtAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.IncorrectClaimException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Jwt Before Filter
 *
 * security config의 addBeforeFilter에 의해 추가되는 Filter이다.
 * OncePerRequestFilter를 상속받아 한 요청당 한번만 해당 Filter를 거쳐간다.
 *
 */
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = getAccessToken(request, response);

        try {
            if (accessToken != null && tokenProvider.validateToken(accessToken)) {
                SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(accessToken));

                filterChain.doFilter(request, response);
            } else {
                responseJwtError(response, JwtExceptionStatus.INVALID_BEARER_TOKEN);
            }
        } catch(IncorrectClaimException e) {
            responseJwtError(response, JwtExceptionStatus.INVALID_TOKEN);
        } catch (UsernameNotFoundException e) {
            responseJwtError(response, JwtExceptionStatus.CAN_NOT_FIND_USER);
        }
    }

    private String getAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bearer = request.getHeader("Authorization");
        log.debug("[bearer] : {}", bearer);

        if(bearer == null || !bearer.startsWith("bearer ")) return null;

        return bearer.substring(7);
    }

    private void responseJwtError(HttpServletResponse response, JwtExceptionStatus status) throws IOException {
        log.debug("response jwt error -> {}", status.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(status.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), status.toCommonResponse());
    }
}
