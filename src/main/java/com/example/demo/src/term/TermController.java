package com.example.demo.src.term;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 약관 동의 여부 등록 API
     * [POST] /terms
     * @return BaseResponse<List<Term>>
     * */
}
