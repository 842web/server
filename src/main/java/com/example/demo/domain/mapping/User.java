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
    private Long userIdx;

    @ManyToOne
    @JoinColumn(name = "boardImgIdx")
    private BoardImage boardImage;

    @Column(length = 30)
    private String instagramId;

    @Column(length = 30)
    private String nickname;

    private String refreshToken;


    private Integer platform;

    private String link;


    private Integer status;

    private Integer read;
}
