package com.example.demo.converter;

import com.example.demo.web.dto.response.UserResponseDto;

public class UserConverter {

    public static UserResponseDto.UserModifyDto toModifyUserDto(String nickname, String instagram_id){

        return UserResponseDto.UserModifyDto.builder()
                .nick_name(nickname)
                .instagram_id(instagram_id)
                .build();
    }

    public static UserResponseDto.UserLinkDto toGetUserLinkDto(String link){

        return UserResponseDto.UserLinkDto.builder()
                .link_info(link)
                .build();

    }

}
