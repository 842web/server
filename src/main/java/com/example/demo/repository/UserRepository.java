package com.example.demo.repository;

import com.example.demo.domain.mapping.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    Optional<UserInfoMapping> findStatusByEmail(String email);

    @Modifying@Transactional
    @Query("update User set instagram_id = :instagram_id where id = :userId")
    void updateUserInstagramId(@Param(value = "instagram_id")String instagram_id, @Param(value = "userId") Long userId);

    @Modifying@Transactional
    @Query("update User set boardImage.id = :board_id where id = :userId")
    void updateUserBoardId(@Param(value = "board_id") Long board_id, @Param(value = "userId") Long userId);

    @Modifying@Transactional
    @Query("update User set createdAt = current_timestamp where id = :userId ")
    void updateUserCreateAt(@Param(value = "userId") Long userId);

    @Modifying @Transactional
    @Query("update User set updatedAt = current_timestamp where id = :userId")
    void updateUserUpdatedAt(@Param(value = "userId")Long userId);
}
