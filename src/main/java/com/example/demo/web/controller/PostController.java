package com.example.demo.web.controller;

import com.example.demo.config.BaseResponse;
import com.example.demo.converter.PostConvertor;
import com.example.demo.service.PostService;
import com.example.demo.web.dto.requestDto.PostRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "포스트")
@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private final PostService postService;

    /**
     * 포스트 작성 API
     * [POST] /posts
     * @return BaseResponse<String>
     * */
    @PostMapping("")
    public BaseResponse<String> writePost(@RequestBody PostRequestDto.CreatePostDto request) {
        // Validation

        try {
            Long post_id = postService.savePost(PostConvertor.toPost(request));
            return new BaseResponse<>(post_id.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<>(e.getMessage());
        }
    }

    /**
     * 포스트 목록 조회 API
     * [GET] /posts?pageSize=?pageNo
     * @return BaseResponse<List<Post>>
     * */

    /**
     * 포스트 상세 조회 API
     * [POST] /posts/:postIdx
     * @return BaseResponse<Post>
     * */

    /**
     * 포스트 정답 확인 API
     * [POST] /posts/answer
     * @return BaseResponse<String>
     * */
}
