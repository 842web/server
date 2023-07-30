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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_img_idx")
    private BoardImage boardImage;

    @Column(length = 30)
    private String instagram_id;

    @Column(length = 30)
    private String nickname;

    private String refresh_token;

    @Column(name = "registrationId")
    private String register_id;

    private String email;
    private Integer platform_info;

    private String link_info;

    private Integer status;

    @Enumerated(EnumType.STRING)
    private Role role;

}
