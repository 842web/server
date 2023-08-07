package com.example.demo.auth.filter;

import com.example.demo.auth.provider.JwtTokenProvider;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.CustomAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import static com.example.demo.config.BaseResponseStatus.EMPTY_JWT;



@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /*
    * Header에서 JWT Token을 추출하고 토큰의 유효성을 검사한다.
    * */


    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void  doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{

            System.out.println(servletRequest.getRequestURI());
            if(servletRequest.getRequestURI().contains("/oauth2/callback") || servletRequest.getRequestURI().contains("/login")){

                filterChain.doFilter(servletRequest, servletResponse);
            }else{
            //1. Request Header 에서 JWT Token 추출
                String token = jwtTokenProvider.resolveToken(servletRequest);
            //2. validateToken 메서드로 토큰 유효성 검사
                if (token != null && jwtTokenProvider.validateToken(token) == true) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                 } else {
                    throw new CustomAuthenticationException(EMPTY_JWT.toString());
                }
                filterChain.doFilter(servletRequest, servletResponse);
            }


    }


}
