package com.example.demo.service.impl;

import com.example.demo.domain.mapping.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
    public void createUserLink(Long userId){

        //링크가 길므로 길이가 20이상일 때 줄임말 처리해줄 것 요청
        String encoded_userId = passwordEncoder.encode(String.valueOf(userId));

        String newchar= encoded_userId.replaceAll("[$/.]","");
        String newchar2 = newchar.substring(0,30);
        while(true){
            if(userRepository.findOneByLink_info(newchar2).isEmpty()){
                break;
            }
                encoded_userId = passwordEncoder.encode(String.valueOf(userId));

                newchar= encoded_userId.replaceAll("[$/.]","");
                newchar2 = newchar.substring(0,30);


        }
        UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http")
                        .host("localhost").path(newchar2).build();
        String url = String.valueOf(uriComponents);
        userRepository.updateUserLinkInfo(url, userId);

    }
}
