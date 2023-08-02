package com.example.demo.web.dto.responseDto;


import lombok.*;

import java.util.List;

public class PostResponseDto {
    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostDto {
        Long postIdx;
        String imageUrl;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostDtoList {
        List<PostDto> postList;
        Integer totalRecords;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostDetailDto {
        Long postIdx;
        String questionerId;
        String questionerName;
        Long answererIdx;
        String question1;
        String question2;
        String question3;
        Long postImageIdx;
        String imageUrl;
    }

}
