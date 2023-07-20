package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postIdx;

    @Column(length = 50)
    private String questionerId;

    @Column(length = 30)
    private String questionerName;

    @Column(length = 1000)
    private String question1;

    @Column(length = 1000)
    private String question2;

    @Column(length = 1000)
    private String question3;

    private String imageUrl;

    private Integer status;

    private Integer read;

    @ManyToOne
    @JoinColumn(name = "postImageIdx")
    private PostImage postImage;

    @ManyToOne
    @JoinColumn(name = "answerIdx")
    private User user;
}