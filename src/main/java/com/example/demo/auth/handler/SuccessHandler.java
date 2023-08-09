package com.example.demo.auth.handler;

import com.example.demo.auth.provider.JwtTokenProvider;

import com.example.demo.repository.UserRepository;
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
    private final UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        try {

            String email = (String) authentication.getName();
            Integer status = userRepository.findStatusByEmail(email).get().getStatus();
            //Optional 객체는 이와 같이 .get()하고 .메소드를 사용해 값을 떼오면 된다.


            System.out.println("status:" );
            if (response.isCommitted()) {
                logger.debug("응답이 이미 커밋된 상태입니다. ");
                return;
            }
            if(status ==2){
                response.getWriter().write(email + "회원가입이 되어 있지 않습니다. 회원가입으로 이동해주세요");
                System.out.println(" 회원가입으로 이동해주세요");
                getRedirectStrategy().sendRedirect(request, response, "http://localhost:8080/users/oauth2/users");
                return;

            }
            String url = makeRedirectUrl(jwtTokenProvider.generateToken(authentication).getAccessToken());
            getRedirectStrategy().sendRedirect(request, response, url); //성공 시점에 redirect

        }catch (Exception err){
            System.out.println(err);
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
