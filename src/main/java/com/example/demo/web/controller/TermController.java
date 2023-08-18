package com.example.demo.web.controller;

import com.example.demo.config.base.BaseException;
import com.example.demo.config.base.BaseResponse;
import com.example.demo.converter.PostConvertor;
import com.example.demo.domain.mapping.Post;
import com.example.demo.domain.mapping.Terms;
import com.example.demo.service.PostService;
import com.example.demo.service.TermService;
import com.example.demo.web.dto.response.PostResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Tag(name = "약관")
@RequestMapping("/terms")
@RestController
@RequiredArgsConstructor
public class TermController {
    @Autowired
    private final TermService termService;

    /**
     * 약관 목록 조회 API
     * [GET] /terms
     * @return BaseResponse<List<Term>>
     * */
    @GetMapping("")
    public BaseResponse<List<Terms>> getTerms() throws BaseException {

        List<Terms> termsList = termService.findTerms();
        return new BaseResponse<>(termsList);
    }

    /**
     * 약관 동의 여부 등록 API
     * [POST] /terms
     * @return BaseResponse<List<Term>>
     * */
}
