package com.example.demo.domain.mapping;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_image_idx")
    private Long id;

    private String url;


}
