package com.example.demo.service.impl;

import com.example.demo.domain.mapping.Post;
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

    // C (Create), U (Update)
    @Override
    public Long savePost(Post post) {
        Post result = postRepository.save(post);
        return result.getId();
    }

    // R (Read)
    @Override
    public Page<Post> findPostPagingCreatedAt(PageRequest pageRequest) {
        return postRepository.findAllByOrderByCreatedAt(pageRequest);
    }
}
