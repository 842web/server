package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long userIdx;
    private Long boardImgIdx;
    private String platform;
    private String id;
    private String accessToken;
    private String refreshToken;
    private String instagramId;
    private String nickname;
    private String link;
    private String status;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
