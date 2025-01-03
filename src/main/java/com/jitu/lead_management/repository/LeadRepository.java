package com.jitu.lead_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.jitu.lead_management.entity.Lead;

import jakarta.transaction.Transactional;

public interface LeadRepository extends JpaRepository<Lead, Integer> {

    List<Lead> findAllByUserId(int userId);

    // @Query("SELECT l FROM Lead l WHERE l.id IN :ids AND l.userId = :userId")
    // List<Lead> findAllByIdAndUserId(@Param("ids") List<Integer> ids,
    // @Param("userId") Integer userId);

    // List<Lead> findAllByLeadIdInAndUserId(List<Integer> ids, int userId);

    Optional<Lead> findByLeadIdAndUserId(int intLeadId, int userId);

    @Modifying
    @Transactional
    void deleteByLeadIdInAndUserId(List<Integer> intLeadIds, int userId);

    @Modifying
    @Transactional
    void deleteAllByUserId(int userId);

    @Modifying
    @Transactional
    void deleteByLeadIdAndUserId(int intLeadId, int userId);

}
