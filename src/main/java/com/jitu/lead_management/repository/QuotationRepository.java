package com.jitu.lead_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jitu.lead_management.entity.Quotation;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Integer> {

}
