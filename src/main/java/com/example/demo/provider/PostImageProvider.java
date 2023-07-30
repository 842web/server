package com.example.demo.provider;

import com.example.demo.config.base.BaseException;
import com.example.demo.domain.mapping.PostImage;

import java.util.List;

public interface PostImageProvider {
    List<PostImage> findAllPostImages() throws BaseException;
}
