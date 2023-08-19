package com.example.demo.repository;

import com.example.demo.domain.mapping.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Terms, Long> {
}
