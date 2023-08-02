package com.example.demo.repository;

import com.example.demo.domain.mapping.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;
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
    @Query("update User set created_At = current_timestamp where id = :userId ")
    void updateUserCreateDate(@Param(value = "userId") Long userId);

    @Modifying @Transactional
    @Query("update User set updated_At = current_timestamp where id = :userId")
    void updateUserUpdatedAt(@Param(value = "userId")Long userId);


    @Query(value = "select User.link_info from User where link_info = :link_info", nativeQuery = true)
    List<String> findOneByLink_info(@Param(value = "link_info")String newchar2);

    @Modifying @Transactional
    @Query("update User set link_info = :link_info where id = :userId")
    void updateUserLinkInfo(@Param(value = "link_info") String url, @Param(value = "userId") Long id);

    @Modifying @Transactional
    @Query("update User set refresh_token = :refreshToken where email = :email")
    void updateUserRefreshToken(@Param(value = "refreshToken") String refreshToken, @Param(value = "email") String email);
}
