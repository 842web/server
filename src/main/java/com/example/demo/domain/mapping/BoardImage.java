package com.example.demo.domain.mapping;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardImgIdx;

    private String url;
}
