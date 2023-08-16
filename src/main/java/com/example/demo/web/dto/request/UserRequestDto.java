package com.example.demo.web.dto.request;

import com.example.demo.domain.mapping.BoardImage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UserRequestDto {


    @Schema(description = "User 생성 회원가입 Dto")
    @Getter @Setter
    public static class CreateUserDto{

        @Schema(description = "이메일")
        @NotEmpty(message = "email이 전송되어야 합니다.")
        private String email;

        @Schema(description = "인스타그램 id")
        @NotEmpty(message = "instagram_id를 입력해주세요")
        private String instagram_id;

        //@NotNull
        //private BoardImage boardImage;

    }

    @Schema(description = "User 수정 Dto")
    @Getter @Setter
    public static class ModifyUserDto{

        @Schema(description = "닉네임")
        private String nickname;

        @Schema(description = "인스타그램 아이디")
        private String instagram_id;

    }

}
