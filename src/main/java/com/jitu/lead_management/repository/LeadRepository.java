package com.jitu.lead_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jitu.lead_management.entity.Lead;

public interface LeadRepository extends JpaRepository<Lead, Integer> {

}
