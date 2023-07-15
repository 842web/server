package com.example.demo.src.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Post {
    private Long postIdx;
    private Long postImageIdx;
    private String questionerId;
    private String questionerName;
    private Long answererIdx;
    private String question1;
    private String question2;
    private String question3;
    private String imageUrl;
    private Integer read;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
