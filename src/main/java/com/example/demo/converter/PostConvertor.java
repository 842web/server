package com.example.demo.converter;

import com.example.demo.domain.mapping.Post;
import com.example.demo.domain.mapping.PostImage;
import com.example.demo.domain.mapping.User;
import com.example.demo.repository.PostImageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.web.dto.requestDto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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

    public static Post toPost(PostRequestDto.CreatePostDto request) {
        User user = staticUserRepository.findById(Long.valueOf(request.getAnswerer_idx())).get();
        PostImage postImage = staticPostImageRepository.findById(Long.valueOf(request.getImageIdx())).get();

        Post post = Post.builder()
                .questioner_id(request.getQuestioner_id())
                .questioner_name(request.getQuestioner_name())
                .question1(request.getQuestion1())
                .question2(request.getQuestion2())
                .question3(request.getQuestion3())
                .imageUrl(request.getImageUrl())
                .postImage(postImage)
                .user(user)
                .build();

        log.info("PostConvertor::toPost() \n" + post.toString());

        return post;
    }
}
