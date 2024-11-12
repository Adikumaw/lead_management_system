package com.jitu.lead_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jitu.lead_management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.userId FROM User u WHERE u.email = ?1")
    Optional<Integer> findUserIdByEmail(String email);

    Optional<User> findByEmail(String email);

    Boolean existsByEmailAndVerified(String email, int verified);

    Boolean existsByUserIdAndVerified(int userId, int verified);

    Boolean existsByEmail(String email);

    @Query("SELECT u.name FROM User u WHERE u.userId = ?1")
    Optional<String> findNameByUserId(int userId);

}
