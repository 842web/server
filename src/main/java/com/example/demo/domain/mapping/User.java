package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@DynamicInsert
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "board_image_idx")
    //private BoardImage boardImage;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,orphanRemoval = true,mappedBy = "user")
    private List<userHasTerms> userHasTerms = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "user")
    private List<Post> post = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Admin admin;
    @Column(length = 30)
    private String instagram_id;

    @Column(length = 30)
    private String nickname;

    private String refresh_token;

    @Column(name = "register_id")
    private String register_id;

    private String email;
    private Integer platform;


    private String Link_info;


    private Integer status;

    @Enumerated(EnumType.STRING)
    private Role role;


}
