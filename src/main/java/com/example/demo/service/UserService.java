package com.example.demo.service;

import com.example.demo.domain.mapping.User;

public interface UserService {

    String createUserInstagram(String user_instagram_id, Long id);

    Long createUserBoardId(Long board_id, Long userId);

    String createUserLink(Long userId);
}
