package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(columnDefinition = "DEFAULT 1")
    private Integer status;

    @Column(columnDefinition = "DEFAULT -1")
    private Integer read;

    @ManyToOne
    @JoinColumn(name = "post_image_idx")
    private PostImage postImage;

    @ManyToOne
    @JoinColumn(name = "answerer_idx")
    private User user;
}
