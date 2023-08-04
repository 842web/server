package com.example.demo.web.controller;

import com.example.demo.auth.provider.JwtTokenProvider;
import com.example.demo.config.BaseResponse;
import com.example.demo.converter.UserConverter;
import com.example.demo.domain.mapping.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.request.UserRequestDto;
import com.example.demo.web.dto.response.UserResponseDto;
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

    public final JwtTokenProvider jwtTokenProvider;
    /**
     * 회원가입 API
     * [POST] /oauth2/users
     * @return BaseResponse<UserResponseDto.TokenInfo>
     * */


    @PostMapping("/oauth2/users")
    public BaseResponse<UserResponseDto.TokenInfo> CreateUserController(@Validated @RequestBody UserRequestDto.CreateUserDto request){

        String user_email = request.getEmail();
        Optional<User> user =  userRepository.findByEmail(request.getEmail());

        if(user.isPresent()) {
            Long userId = user.get().getId();

            if (!request.getInstagram_id().isEmpty()) {
                String user_instagram_id = request.getInstagram_id();
                String changed_instagram_id = userService.createUserInstagram(user_instagram_id, userId);
                if (request.getInstagram_id() != changed_instagram_id) {
                    //Exception
                }
                ;
            }

            //board_img set

            Long board_id = request.getBoardImage().getId();
            Long changed_board_id = userService.createUserBoardId(board_id, userId);
            if (board_id != changed_board_id) {
                //Exception
                System.out.println("err here");
            }


            //createdAt, updatedAt update
            userRepository.updateUserCreateAt(userId);
            userRepository.updateUserUpdatedAt(userId);

            String user_link = userService.createUserLink(userId);


            //jwtToken발급
            String RefreshToken = jwtTokenProvider.generateToken(user_email).getAccessToken();

            userRepository.updateUserRefreshToken(RefreshToken, userId);

            //응답
            return new BaseResponse<>(jwtTokenProvider.generateToken(user_email));
        }
        return null;
    }



    /**
     * 회원 정보 수정 API
     * [PATCH] /users
     * @return BaseResponse<String>
     * */
    @Tag(name = "users", description="회원 정보 수정 API")
    @PatchMapping("/")
    public BaseResponse<UserResponseDto.UserModifyDto> UserModify(@Validated @RequestBody UserRequestDto.ModifyUserDto request, Long userId){

        var user_nickname="";
        var user_instagram_id="";
        if(request.getNickname()!= null){
            user_nickname = userService.updateUsernickname(request.getNickname(),userId);
        }else{user_nickname = userRepository.findById(userId).get().getNickname();

        }

        if(request.getInstagram_id() !=null){

            user_instagram_id = userService.updateUserInstagram_id(request.getInstagram_id(),userId);
        }else{
            user_instagram_id =userRepository.findById(userId).get().getInstagram_id();
        }


        return new BaseResponse<>(UserConverter.toModifyUserDto(user_nickname,user_instagram_id));


    }



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
     * 공유링크조회 API
     * [GET] /users/link
     * @return BaseResponse<String>
     * */
    @Tag(name = "users", description="회원 Link 조회")
    @GetMapping("/link")
    public BaseResponse<UserResponseDto.UserLinkDto> UserDetailLink(Long userId){

        String user_link = userService.findUserLink(userId);

        return new BaseResponse<>(UserConverter.toGetUserLinkDto(user_link));
    }



}
