package com.example.demo.auth.config;

import com.example.demo.auth.filter.JwtAuthenticationFilter;
import com.example.demo.auth.handler.FailureHandler;
import com.example.demo.auth.handler.SuccessHandler;
import com.example.demo.auth.provider.JwtTokenProvider;
import com.example.demo.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfigure {

    /*
    * Spring Security의 환경설정을 하는 Class이다.
    * 주요 역할: 인증, 인가
    * JWT 처리를 위한 Fliter을 @Bean으로 등록할 것
    *
    *
    *  */

    private final CustomOAuth2UserService customOAuth2UserService;

    private final JwtTokenProvider jwtTokenProvider;

    private final FailureHandler failureHandler;

    private final SuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //disable 설정
       http
                .httpBasic().disable() //HTTP Basic Auth 기반 로그인 창이 뜨지 않는다.
                .csrf().disable() // REST API의 경우 csrf 보안이 필요하지 않는다.
                .formLogin().disable() //formLogin disable
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                //세션 사용하지 않음 >> JWT 등을 사용할 때 사용하는 것

        //리소스 인증 및 권한 설정
        http.authorizeRequests()
                .antMatchers("/users/oauth2/**").permitAll()
                .antMatchers("/posts/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/terms/**").permitAll()
                .antMatchers("/postImages/**").authenticated();


        //oauth2Login 로직 중요!!
            http.oauth2Login()
                    .authorizationEndpoint().baseUri("/oauth2/authorize")
                    .and()
                    .redirectionEndpoint().baseUri("/oauth2/callback/**")
                    .and()
                    .userInfoEndpoint().userService(customOAuth2UserService)
                    .and()
                    .successHandler(successHandler);

                    //.failureHandler(failureHandler);


        //jwt filter 설정
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build(); //??에러 안나게 일단 넣어는 놓았다.

    }

}
