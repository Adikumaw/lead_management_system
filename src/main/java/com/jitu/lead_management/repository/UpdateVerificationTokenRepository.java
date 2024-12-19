package com.jitu.lead_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jitu.lead_management.entity.UpdateVerificationToken;

public interface UpdateVerificationTokenRepository extends JpaRepository<UpdateVerificationToken, Integer> {
    // UpdateVerificationToken findByToken(String token);

    // UpdateVerificationToken findByUserId(int UserId);

    Optional<UpdateVerificationToken> findByData(String data);

    Optional<UpdateVerificationToken> findByUserIdAndData(int userId, String data);

    Optional<UpdateVerificationToken> findByUserIdAndDataStartingWith(int userId, String prefix);
}
