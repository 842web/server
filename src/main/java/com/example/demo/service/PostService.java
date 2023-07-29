package com.example.demo.service;

import com.example.demo.config.BaseException;
import com.example.demo.domain.mapping.Post;

public interface PostService {
    Long savePost(Post post) throws BaseException;
}
