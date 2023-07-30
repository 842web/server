package com.example.demo.web.controller;

import com.example.demo.config.base.BaseException;
import com.example.demo.config.base.BaseResponse;
import com.example.demo.converter.PostConvertor;
import com.example.demo.domain.mapping.Post;
import com.example.demo.service.PostService;
import com.example.demo.web.dto.requestDto.PostRequestDto;
import com.example.demo.web.dto.responseDto.PostResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    public BaseResponse<String> writePost(@Valid @RequestBody PostRequestDto.CreatePostDto request) throws BaseException {
        // TODO: Validation, Exception

        Long post_id = postService.savePost(PostConvertor.toPost(request));
        return new BaseResponse<>(post_id.toString());
    }

    /**
     * 포스트 목록 조회 API
     * [GET] /posts?pageSize=?pageNo
     * @return BaseResponse<List<Post>>
     * */
    @GetMapping("")
    public BaseResponse<PostResponseDto.PostDtoList> getPost(@RequestParam Integer pageSize, @RequestParam Integer pageNo) {
        // TODO: Validation, Exception

        try {
            Page<Post> posts = postService.findPostPagingCreatedAt(PageRequest.of(pageNo, pageSize));
            return new BaseResponse<>(PostConvertor.toPostDtoList(posts.toList(), posts.getNumberOfElements()));
        } catch (BaseException e) {
            e.printStackTrace();
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 포스트 상세 조회 API
     * [POST] /posts/:postIdx
     * @return BaseResponse<Post>
     * */
    @GetMapping("{postIdx}")
    public BaseResponse<PostResponseDto.PostDto> getPostDetail(@PathVariable Long postIdx) throws BaseException {
        // TODO: Validation, Exception
        Post post = postService.findPostById(postIdx);
        return new BaseResponse<>(PostConvertor.toPostDto(post));
    }

    /**
     * 포스트 정답 확인 API
     * [POST] /posts/answer
     * @return BaseResponse<String>
     * */
    @PostMapping("/answer")
    public BaseResponse<String> checkAnswer(@Valid @RequestBody PostRequestDto.UpdatePostReadDto request) {
        // TODO: Validation, Exception

        try {
            Boolean result = postService.checkPostAnswer(Long.valueOf(request.getPostIdx()), request.getAnswer());
            return new BaseResponse<>(result.toString());
        } catch (BaseException e) {
            e.printStackTrace();
            return new BaseResponse<>(e.getStatus());
        }
    }
}
