package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_idx")
    private Long id;

    @Column(length = 45)
    private String phone;

    private Integer status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

}
