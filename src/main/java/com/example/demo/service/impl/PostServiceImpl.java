package com.example.demo.service.impl;

import com.example.demo.config.BaseException;
import com.example.demo.domain.mapping.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Long savePost(Post post) {
        Post result = postRepository.save(post);
        return result.getId();
    }
}
