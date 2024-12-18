package com.jitu.lead_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jitu.lead_management.entity.ResetRequest;

@Repository
public interface ResetRequestRepository extends JpaRepository<ResetRequest, Integer> {

}
