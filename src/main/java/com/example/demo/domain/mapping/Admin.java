package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.enums.Status;
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
    private Long id;

    @Column(length = 45)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Status status;

    //@OneToOne
    //@JoinColumn(name = "userIdx")
    //private User user;

}
