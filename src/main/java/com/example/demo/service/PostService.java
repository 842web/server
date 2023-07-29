package com.example.demo.service;

import com.example.demo.config.BaseException;
import com.example.demo.domain.mapping.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PostService {


    // C (Create), U (Update)
    Long savePost(Post post) throws BaseException;

    // R (Read)
    Page<Post> findPostPagingCreatedAt(PageRequest pageRequest) throws BaseException;

    Post findPostById(Long postIdx) throws BaseException;

    // R (Check)
    boolean checkPostAnswer(Long postIdx, String answer) throws BaseException;

    // D (Delete)
}
