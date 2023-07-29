package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_idx")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_image_idx")
    private BoardImage boardImage;

    @Column(length = 30)
    private String instagram_id;

    @Column(length = 30)
    private String nickname;

    private String refresh_token;


    private Integer platform;

    private String link;


    private Integer status;

}
