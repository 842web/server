package com.example.demo.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
public class PostRequestDto {
    @Getter
    @Setter
    @ToString
    public static class CreatePostDto{
        @NotBlank(message = "질문자 아이디는 필수값입니다.")
        String questioner_id;
        @NotBlank(message = "질문자 이름은 필수값입니다.")
        String questioner_name;
        @NotBlank(message = "답변자 인덱스는 필수값입니다.")
        String answerer_idx;
        @NotBlank(message = "질문1은 필수값입니다.")
        String question1;
        @NotBlank(message = "질문2는 필수값입니다.")
        String question2;
        @NotBlank(message = "질문3은 필수값입니다.")
        String question3;
        @NotBlank(message = "포스트 이미지 인덱스는 필수값입니다.")
        String imageIdx;
    }

    @Getter
    @Setter
    @ToString
    public static class UpdatePostReadDto{
        @NotNull(message = "포스트 인덱스는 필수값입니다.")
        Long postIdx;
        @NotBlank(message = "답변은 필수값입니다.")
        String answer;
    }
}
