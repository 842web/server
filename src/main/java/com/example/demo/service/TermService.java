package com.example.demo.service;

import com.example.demo.config.base.BaseException;
import com.example.demo.domain.mapping.Post;
import com.example.demo.domain.mapping.Terms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TermService {

    // R (Read)
    List<Terms> findTerms() throws BaseException;

}
