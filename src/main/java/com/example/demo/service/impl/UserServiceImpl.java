package com.example.demo.service.impl;

import com.example.demo.domain.mapping.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static org.apache.tomcat.util.net.openssl.ciphers.Encryption.AES256;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Override
    public String createUserInstagram(String user_instagram_id, Long id){

        userRepository.updateUserInstagramId(user_instagram_id,id);

        return userRepository.findById(id).get().getInstagram_id();

    }

    @Override
    public Long createUserBoardId(Long board_id, Long userId){

        userRepository.updateUserBoardId(board_id, userId);

        return userRepository.findById(userId).get().getBoardImage().getId();
    }

    @Override
    public String createUserLink(Long userId){

        //userid에 대해 암호화한 문자열로 링크를 생성한다.



        return userRepository.findById(userId).get().getLink_info();
    }

    @Override
    public String updateUsernickname(String nickname, Long userId){

        userRepository.updateUserNickname(nickname, userId);

        return userRepository.findById(userId).get().getNickname();
    };

    @Override
    public String updateUserInstagram_id(String instagram_id, Long userId){

        userRepository.updateUserInstagramId(instagram_id,userId);

        return userRepository.findById(userId).get().getInstagram_id();
    };


    @Override
    public String findUserLink(Long userId){

        return userRepository.findById(userId).get().getLink_info();
    };

}

