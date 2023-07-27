package com.example.demo.auth.handler;

import com.example.demo.auth.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SuccessHandler  extends SimpleUrlAuthenticationSuccessHandler {

    /*
    * 인증 성공시 JWT토큰 발급
    * */

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        try {

            String url = makeRedirectUrl(jwtTokenProvider.generateToken(authentication).getAccessToken());
            System.out.println(url);
            if (response.isCommitted()) {
                logger.debug("응답이 이미 커밋된 상태입니다. ");
                return;
            }

            getRedirectStrategy().sendRedirect(request, response, url); //성공 시점에 redirect

        }catch (Exception e){
            System.out.println(e);
        }

    }

    private String makeRedirectUrl(String token) {
        try {
            return UriComponentsBuilder.fromUriString("http://localhost:8080/oauth2/callback")
                    .queryParam("token", token)
                    .build().toUriString();
        }catch(Exception err){
            System.out.println(err);
            return null;
        }
    }




}
