package com.example.demo.web.controller;

import com.example.demo.config.BaseResponse;
import com.example.demo.domain.mapping.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.request.UserRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Tag(name = "유저")
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;
    public final UserRepository userRepository;
    /**
     * 회원가입 API
     * [POST] /oauth2/users
     * @return BaseResponse<String>
     * */
    @PostMapping("/oauth2/users")
    public BaseResponse<String> CreateUserController(@Validated @RequestBody UserRequestDto.CreateUserDto request){

        Optional<User> user =  userRepository.findByEmail(request.getEmail());

        if(user.isPresent()) {
            Long userId = user.get().getId();

            if (!request.getInstagram_id().isEmpty()) {
                String user_instagram_id = request.getInstagram_id();
                String changed_instagram_id = userService.createUserInstagram(user_instagram_id, userId);
                if(request.getInstagram_id()!=changed_instagram_id){
                    //Exception
                };
            }

            //board_img set

            Long board_id = request.getBoardImage().getId();
            System.out.println(board_id);
            Long changed_board_id = userService.createUserBoardId(board_id, userId);
            if(board_id != changed_board_id){
                //Exception
            }

            //createdAt, updatedAt update
            userRepository.updateUserCreateDate(userId);
            userRepository.updateUserUpdatedAt(userId);


            //LINK생성 및 저장
            userService.createUserLink(userId);


            //회원 약관 동의



            //토큰 발급 및 저장


            //response 넣어주기


        }

        return null;
    }



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
     * 공유링크 조회 API
     * [GET] /users/link
     * @return BaseResponse<String>
     * */
}
