package com.example.demo.web.controller;

import com.example.demo.config.base.BaseException;
import com.example.demo.config.base.BaseResponse;
import com.example.demo.domain.mapping.PostImage;
import com.example.demo.provider.PostImageProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "테스트")
@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private final PostImageProvider postImageProvider;

    /**
     * JPA 사용 테스트
     * */
    @Operation(summary = "JPA 연결 테스트 API", description = "이런거")
    @GetMapping("/postImages")
    @ApiResponse(responseCode = "200", description = "성공")
    public BaseResponse<List<PostImage>> getPostImagesWithJPA() {
        try {
            List<PostImage> getPostImages = postImageProvider.findAllPostImages();
            return new BaseResponse<>(getPostImages);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}