package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "term_idx")
    private Long id;

    @Column(length = 200)
    private String title;

    private String desc;

    private Integer status;


}
