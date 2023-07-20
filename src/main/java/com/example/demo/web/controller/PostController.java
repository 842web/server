package com.example.demo.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "포스트")
@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    /**
     * 포스트 작성 API
     * [POST] /posts
     * @return BaseResponse<String>
     * */

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
