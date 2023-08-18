package com.example.demo.converter;

import com.example.demo.config.base.BaseException;
import com.example.demo.config.base.Code;
import com.example.demo.domain.mapping.Post;
import com.example.demo.domain.mapping.PostImage;
import com.example.demo.domain.mapping.User;
import com.example.demo.repository.PostImageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.web.dto.request.PostRequestDto;
import com.example.demo.web.dto.response.PostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostConvertor {
    // Final Repository
    private final UserRepository userRepository;
    private final PostImageRepository postImageRepository;

    // Static Repository
    private static UserRepository staticUserRepository;
    private static PostImageRepository staticPostImageRepository;

    @PostConstruct
    public void init() {
        staticUserRepository = this.userRepository;
        staticPostImageRepository = this.postImageRepository;
    }

    public static Post toPost(PostRequestDto.CreatePostDto request) throws BaseException {
        User user = staticUserRepository.findById(Long.valueOf(request.getAnswerer_idx())).orElseThrow(()-> new BaseException(Code.USER_NOT_FOUND));
        PostImage postImage = staticPostImageRepository.findById(Long.valueOf(request.getImageIdx())).orElseThrow(()-> new BaseException(Code.POST_IMAGE_NOT_FOUND));

        Post post = Post.builder()
                .questionerId(request.getQuestioner_id())
                .questionerName(request.getQuestioner_name())
                .question1(request.getQuestion1())
                .question2(request.getQuestion2())
                .question3(request.getQuestion3())
                .postImage(postImage)
                .user(user)
                .build();

        log.info("PostConvertor::toPost() \n" + post.toString());

        return post;
    }

    public static PostResponseDto.PostDto toPostDto(Post post) {
        return PostResponseDto.PostDto.builder()
                .postIdx(post.getId())
                .imageUrl(post.getImageUrl())
                .build();
    }

    public static PostResponseDto.PostDetailDto toPostDetailDto(Post post) {
        return PostResponseDto.PostDetailDto.builder()
                .postIdx(post.getId())
                .questionerId(post.getQuestionerId())
                .questionerName(post.getQuestionerName())
                .answererIdx(post.getUser().getId())
                .postImageIdx(post.getPostImage().getId())
                .imageUrl(post.getImageUrl())
                .question2(post.getQuestion2())
                .question3(post.getQuestion3())
                .question1(post.getQuestion1())
                .question2(post.getQuestion2())
                .question3(post.getQuestion3())
                .build();
    }

    public static PostResponseDto.PostDtoList toPostDtoList(List<Post> postList, Integer totalRecords) {
        List<PostResponseDto.PostDto> postDtoList =
                postList.stream()
                        .map(PostConvertor::toPostDto)
                        .collect(Collectors.toList());

        return PostResponseDto.PostDtoList.builder()
                .postList(postDtoList)
                .totalRecords(totalRecords)
                .build();
    }
}
