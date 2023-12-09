package com.sparta.easydelivery.domain.user.repository;

import com.sparta.easydelivery.domain.user.entity.PasswordHistory;
import com.sparta.easydelivery.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {
    List<PasswordHistory> findAllByUserOrderByCreatedAtDesc(User user);
}
