package com.example.demo.auth.handler;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.config.CustomAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class JwtAuthenticationExceptionHandler extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            System.out.println("exptionHandler");
            filterChain.doFilter(request,response);
        }catch(CustomAuthenticationException authenticationException){
            System.out.println("gg");
            PrintWriter writer= response.getWriter();
            String status = authenticationException.getMessage();
            String result = "{ isSucess: false \n" + "code: "+ response.getStatus()+"\n message: "+status+"}";
            writer.write(result.toString());
            writer.flush();
            writer.close();
        }catch(Exception err){

            System.out.println(err);
        }

    }
}
