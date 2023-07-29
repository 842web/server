package com.example.demo.web.dto.requestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
public class PostRequestDto {
    @Getter
    @Setter
    @ToString
    public static class CreatePostDto{
        @NotBlank
        String questioner_id;
        @NotBlank
        String questioner_name;
        @NotBlank
        String answerer_idx;
        @NotBlank
        String question1;
        @NotBlank
        String question2;
        @NotBlank
        String question3;
        @NotBlank
        String imageIdx;
        @NotBlank
        String imageUrl;
    }

    @Getter
    @Setter
    @ToString
    public static class UpdatePostReadDto{
        @NotBlank
        String postIdx;
        @NotBlank
        String answer;
    }
}
