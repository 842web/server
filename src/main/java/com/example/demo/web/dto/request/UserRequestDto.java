package com.example.demo.web.dto.request;

import com.example.demo.domain.mapping.BoardImage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UserRequestDto {


    @Getter @Setter
    public static class CreateUserDto{

        @NotEmpty(message = "email이 전송되어야 합니다.")
        private String email;

        @NotEmpty(message = "instagram_id를 입력해주세요")
        private String instagram_id;

        @NotNull
        private BoardImage boardImage;

    }

    @Getter @Setter
    public static class ModifyUserDto{

        private String nickname;

        private String instagram_id;

    }

}
