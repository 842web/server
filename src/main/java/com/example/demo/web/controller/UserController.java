package com.example.demo.web.controller;


import com.example.demo.auth.provider.JwtTokenProvider;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.config.CustomAuthenticationException;
import com.example.demo.converter.UserConverter;

import com.example.demo.config.base.BaseResponse;



import com.example.demo.domain.mapping.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.request.UserRequestDto;
import com.example.demo.web.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.example.demo.config.BaseResponseStatus.EMPTY_REFRESH_TOKEN;

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


    @PostMapping("/signup")
    public BaseResponse<UserResponseDto.TokenInfo> CreateUserController(@Validated @RequestBody(required = false) UserRequestDto.CreateUserDto request){


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

            //userstate 변경
            userService.updateUserStatus(1, userId);

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
    @GetMapping("/autologin")
    public BaseResponse<UserResponseDto.TokenInfo> UserDetailsTokenInfo(@RequestHeader(value = "Authorization") String authorizationHeader, @RequestHeader(value = "Refresh-Token",required = false) String RefreshHeader, HttpServletRequest request) {
        try {
            //access token 이 만료되었으니 jwtfilter를 정상적으로 통과할 수 없을 것이다. 따라서 jwtfilter에서 제외해주어야 한다.
            //따라서 access token 에 대해 check하는 기능이 필요하다.
            //단 expired의 경우 check하지 않는다. 혹은 expired가 체크되어야만 할 수 있게 하든가 > 이건 좀 아닌 듯

            if(authorizationHeader == null){
                throw new CustomAuthenticationException("EMPTY ACCESS TOKEN");
            }

            String access_token = jwtTokenProvider.resolveToken(request);
            jwtTokenProvider.validateAccessToken(access_token);
            if (RefreshHeader == null) {
                System.out.println("here");
            }
            //refersh token 전형 vaildaToken 이 필요할 듯
            jwtTokenProvider.RefreshValidateToken(RefreshHeader);
            //refresh token 조차 만료되었을 시 자동로그인을 포기하고 다시 로그인 해야한다.

            //refresh token 이 만료되지 않았을 경우 아래 로직 실행
            //access token 복호화
            String user_email = jwtTokenProvider.getAuthentication(access_token).getName();
            System.out.println(user_email);
            String user_refresh_token = userRepository.findByEmail(user_email).get().getRefresh_token();
            System.out.println(user_refresh_token);
            System.out.println(RefreshHeader);
            if (user_refresh_token.equals(RefreshHeader)) {
                //==로 했을 때는 일치 하지 않았다.
                //access token 재발급
                return new BaseResponse<>(jwtTokenProvider.generateAccessToken(user_email));
            } else {
                System.out.println("refresh token 불일치");
                return new BaseResponse(BaseResponseStatus.INVALID_REFRESH_TOKEN);
            }


        }catch (CustomAuthenticationException ee){

            System.out.println(ee.getMessage());
            return new BaseResponse(false, ee.getMessage(),400);
        }


    }



    // refresh token 을 받아서 본다.

    /**
     * 회원탈퇴 API
     * [POST] /users/withdraw
     * @return BaseResponse<String>
     * */

    @PostMapping("/withdraw")
    public BaseResponse<UserResponseDto.UserWithdrawDto> UserRemove(@RequestParam( "userId") Long userId){

        Integer status = userService.updateUserStatus(3, userId);
        String nickname = userRepository.findById(userId).get().getNickname();
        return new BaseResponse(UserConverter.toWirthdrawDto(status, nickname));


    }


    /**
     * 공유링크조회 API
     * [GET] /users/link
     * @return BaseResponse<String>
     * */
    @Tag(name = "users", description="회원 Link 조회")
    @GetMapping("/link")
    public BaseResponse<UserResponseDto.UserLinkDto> UserDetailLink(@RequestParam( "userId") Long userId){

        System.out.println(userId);
        String user_link = userService.findUserLink(userId);

        return new BaseResponse<>(UserConverter.toGetUserLinkDto(user_link));
    }



}
