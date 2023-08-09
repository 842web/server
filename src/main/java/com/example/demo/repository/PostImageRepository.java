package com.example.demo.repository;

import com.example.demo.domain.mapping.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
