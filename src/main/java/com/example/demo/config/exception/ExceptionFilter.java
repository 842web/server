package com.example.demo.config.exception;

import com.example.demo.config.base.BaseResponse;
import com.example.demo.config.base.Code;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("exception filter: " + e.getMessage());
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, e);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable e) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        BaseResponse baseResponse = new BaseResponse(Code.INTERNAL_SERVER_ERROR, e.getMessage());

        try {
            String json = new ObjectMapper().writeValueAsString(baseResponse);
            response.getWriter().write(json);
        } catch (IOException ex) {
            e.printStackTrace();
        }
    }
}
