package com.example.demo.web.controller;

import com.example.demo.config.base.BaseException;
import com.example.demo.config.base.BaseResponse;
import com.example.demo.config.base.Code;
import com.example.demo.converter.PostConvertor;
import com.example.demo.domain.mapping.Post;
import com.example.demo.service.ImageService;
import com.example.demo.service.PostService;
import com.example.demo.utils.CryptographyUtils;
import com.example.demo.utils.ValidationRegex;
import com.example.demo.web.dto.request.PostRequestDto;
import com.example.demo.web.dto.response.PostResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;


@Slf4j
@Tag(name = "포스트")
@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private final PostService postService;

    @Autowired
    private final ImageService imageService;

    /**
     * 포스트 작성 API
     * [POST] /posts
     * @return BaseResponse<String>
     * */
    @Valid
    @PostMapping("")
    public BaseResponse<String> writePost(@RequestPart("image") MultipartFile image, @RequestPart("postDto") @Valid PostRequestDto.CreatePostDto createPostDto) throws BaseException, IOException {
        if (!ValidationRegex.isImageFile(image)) throw new BaseException(Code.INVALID_FILE_TYPE);

        String imageUrl = imageService.uploadImage(image, "image");

        Post post = PostConvertor.toPost(createPostDto);
        post.setImageUrl(imageUrl);

        Long post_id = postService.savePost(post);
        return new BaseResponse<>(post_id.toString());
    }

    /**
     * 포스트 목록 조회 API
     * [GET] /posts?pageSize=&pageNo=&link=
     * @return BaseResponse<List<Post>>
     * */
    @GetMapping("")
    public BaseResponse<PostResponseDto.PostDtoList> getPost(
            @NotBlank(message = "링크 난수 값은 필수값입니다.") @RequestParam String link,
            @NotNull(message = "페이지 사이즈는 필수값입니다.") @RequestParam Integer pageSize,
            @NotNull(message = "페이지 번호는 필수값입니다.") @RequestParam Integer pageNo) throws Exception {

        CryptographyUtils cryptographyUtils = new CryptographyUtils();
        Long userIdx = Long.valueOf(cryptographyUtils.decrypt(link));

        Page<Post> posts = postService.findPostByUserIdx(userIdx, PageRequest.of(pageNo, pageSize));

        return new BaseResponse<>(PostConvertor.toPostDtoList(posts.toList(), posts.getNumberOfElements()));
    }

    /**
     * 포스트 상세 조회 API
     * [POST] /posts/:postIdx
     * @return BaseResponse<Post>
     * */
    @GetMapping("{postIdx}")
    public BaseResponse<PostResponseDto.PostDetailDto> getPostDetail(@NotBlank(message = "포스트 번호는 필수값입니다.") @PathVariable Long postIdx) throws BaseException {
        Post post = postService.findPostById(postIdx);
        return new BaseResponse<>(PostConvertor.toPostDetailDto(post));
    }

    /**
     * 포스트 정답 확인 API
     * [POST] /posts/answer
     * @return BaseResponse<String>
     * */
    @PostMapping("/answer")
    public BaseResponse<String> checkAnswer(@Valid @RequestBody PostRequestDto.UpdatePostReadDto request) throws BaseException {
        boolean result = postService.checkPostAnswer(request.getPostIdx(), request.getAnswer());
        return new BaseResponse<>(Boolean.toString(result));
    }
}
