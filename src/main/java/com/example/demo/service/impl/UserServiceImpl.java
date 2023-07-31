package com.example.demo.service.impl;

import com.example.demo.domain.mapping.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
