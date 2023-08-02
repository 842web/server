package com.example.demo.service;

import com.example.demo.domain.mapping.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {

    String createUserInstagram(String user_instagram_id, Long id);

    Long createUserBoardId(Long board_id, Long userId);

    void createUserLink(Long userId);
}
