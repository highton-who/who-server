package com.pick.zick.global.security;

import com.pick.zick.global.exception.BusinessException;
import com.pick.zick.global.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtTokenProvider;

    // 인증이 필요 없는 경로 리스트
    private static final List<String> EXCLUDE_URLS = List.of("/who/", "/zick/auth/", "/zick/ping");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        // 1. 화이트리스트 경로는 토큰 검사 없이 바로 다음 필터로 진행
        boolean isExcluded = EXCLUDE_URLS.stream().anyMatch(path::startsWith);

        if (isExcluded) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. 토큰 추출 및 검증
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            if (jwtTokenProvider.validToken(token)) {
                Authentication authentication = jwtTokenProvider.authentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // 토큰이 유효하지 않을 때만 예외 발생
                throw new BusinessException(ErrorCode.INVALID_TOKEN);
            }
        }

        // 3. 다음 필터 진행
        filterChain.doFilter(request, response);
    }
}