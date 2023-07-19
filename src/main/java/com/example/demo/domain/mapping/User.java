package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.enums.Platform;
import com.example.demo.domain.enums.Read;
import com.example.demo.domain.enums.Status;
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

    @Column(length = 30)
    private String instagramId;

    @Column(length = 30)
    private String nickname;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private String link;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Read read;
}
