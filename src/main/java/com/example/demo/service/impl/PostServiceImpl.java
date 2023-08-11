package com.example.demo.service.impl;

import com.example.demo.config.base.BaseException;
import com.example.demo.config.base.Code;
import com.example.demo.domain.mapping.Post;
import com.example.demo.repository.PostImageRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostImageRepository postImageRepository;

    // C (Create), U (Update)
    @Override
    public Long savePost(Post post) {
        Post result = postRepository.save(post);
        return result.getId();
    }


    // R (Read)
    @Override
    public Page<Post> findPostPagingCreatedAt(PageRequest pageRequest) {
        return null;//postRepository.findAllByOrderByCreatedAt(pageRequest);
    }



    @Override
    public Post findPostById(Long postIdx) throws BaseException {
        return postRepository.findById(postIdx).orElseThrow(()->new BaseException(Code.POST_NOT_FOUND));
    }

    @Override
    public boolean checkPostAnswer(Long postIdx, String answer) throws BaseException {
        Post post = postRepository.findById(postIdx).orElseThrow(()-> new BaseException(Code.POST_NOT_FOUND));

        boolean isAnswer = post.getQuestionerId().equals(answer) || post.getQuestionerName().equals(answer);
        if (isAnswer) {
            post.setReadInfo(1);
            postRepository.save(post);

            return true;
        }

        return false;
    }
}
