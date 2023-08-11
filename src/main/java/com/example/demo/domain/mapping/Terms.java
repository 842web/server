package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_idx")
    private Long id;

    @Column(length = 200)
    private String title;

    private String contents;

    private Integer status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,mappedBy = "terms")
    private List<userHasTerms> userHasTermsList = new ArrayList<>();


    //
}
