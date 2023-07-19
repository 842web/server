package com.example.demo.domain.mapping;

import com.example.demo.domain.common.BaseEntity;
import com.example.demo.domain.enums.Status;
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
    private Long termIdx;

    @Column(length = 200)
    private String title;

    private String desc;

    private Status status;

}
