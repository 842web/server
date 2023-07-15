package com.example.demo.src.term.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Term {
    private Long termIdx;
    private String title;
    private String desc;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
