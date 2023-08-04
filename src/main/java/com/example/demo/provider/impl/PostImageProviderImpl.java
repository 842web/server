package com.example.demo.provider.impl;

import com.example.demo.config.base.BaseException;
import com.example.demo.config.base.Code;
import com.example.demo.domain.mapping.PostImage;
import com.example.demo.provider.PostImageProvider;
import com.example.demo.repository.PostImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostImageProviderImpl implements PostImageProvider {
    @Autowired
    PostImageRepository postImageRepository;

    @Override
    public List<PostImage> findAllPostImages() throws BaseException {
        try {
            return postImageRepository.findAll();
        } catch (Exception exception) {
            throw new BaseException(Code.DATABASE_ERROR);
        }
    }
}
