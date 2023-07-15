package com.example.demo.src.image.model;

import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostImage {
    @SchemaProperty(name = "포스트 이미지 인덱스")
    private Long postImageIdx;
    @SchemaProperty(name = "포스트 이미지 url")
    private String url;
}
