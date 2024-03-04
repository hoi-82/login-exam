package com.example.login.loginexam.filter;

import com.example.login.loginexam.component.JwtTokenProvider;
import com.example.login.loginexam.domain.enums.JwtExceptionStatus;
import com.example.login.loginexam.exception.JwtAuthenticationException;
import io.jsonwebtoken.IncorrectClaimException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = getAccessToken(request);

        try {
            if (tokenProvider.validateToken(accessToken)) {
                SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(accessToken));
            }
        } catch(IncorrectClaimException e) {
            throw new JwtAuthenticationException(JwtExceptionStatus.INVALID_TOKEN);
        } catch (UsernameNotFoundException e) {
            throw new JwtAuthenticationException(JwtExceptionStatus.CAN_NOT_FIND_USER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");

        if(bearer == null || !bearer.startsWith("bearer ")) throw new JwtAuthenticationException(JwtExceptionStatus.INVALID_BEARER_TOKEN);

        return bearer.substring(7);
    }
}
