package com.example.demo.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfo {
        private String grantType;
        private String accessToken;
        private Long accessTokenExpirationTime;
        private String refreshToken;
        private Long refreshTokenExpirationTime;
    }

    @Builder //
    @Getter //
    @AllArgsConstructor //
    public static class UserLinkDto {

        private String link_info;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UserModifyDto {

        private String nick_name;

        private String instagram_id;
    }




}
