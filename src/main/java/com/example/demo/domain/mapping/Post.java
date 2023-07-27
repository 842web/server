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
    @Column(name = "post_idx")
    private Long id;

    @Column(length = 50)
    private String questioner_id;

    @Column(length = 30)
    private String questioner_name;

    @Column(length = 1000)
    private String question1;

    @Column(length = 1000)
    private String question2;

    @Column(length = 1000)
    private String question3;

    private String imageUrl;

    private Integer status;

    private Integer read_info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_image_idx")
    private PostImage postImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_idx")
    private User user;
}
