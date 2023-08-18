package com.example.demo.service.impl;

import com.example.demo.domain.mapping.Terms;
import com.example.demo.repository.TermRepository;
import com.example.demo.service.TermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class TermServiceImpl implements TermService {
    @Autowired
    TermRepository termRepository;

    @Override
    public List<Terms> findTerms() {
        return termRepository.findAll();
    }
}
