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

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null ) {
            if(jwtTokenProvider.validToken(token)){
                Authentication authentication = jwtTokenProvider.authentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                throw new BusinessException(ErrorCode.INVALID_TOKEN);
            }
        }
        filterChain.doFilter(request,response);
    }
}
