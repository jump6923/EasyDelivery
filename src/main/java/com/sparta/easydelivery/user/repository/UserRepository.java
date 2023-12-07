package com.sparta.easydelivery.user.repository;

import com.sparta.easydelivery.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAll();

    Optional<User> findByKakaoId(Long kakaoId);

    Optional<User> findByEmail(String email);

    Optional<User> findByNaverId(String id);
}
