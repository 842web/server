package com.example.demo.src.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "유저")
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<String>
     * */

    /**
     * 회원 정보 수정 API
     * [PATCH] /users
     * @return BaseResponse<String>
     * */

    /**
     * 자동 로그인 API
     * [GET] /users/autologin
     * @return BaseResponse<String>
     * */

    /**
     * 회원탈퇴 API
     * [POST] /users/withdraw
     * @return BaseResponse<String>
     * */

    /**
     * 회원가입 API
     * [GET] /users/link
     * @return BaseResponse<String>
     * */
}
