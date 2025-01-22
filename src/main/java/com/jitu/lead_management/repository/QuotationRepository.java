package com.jitu.lead_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.jitu.lead_management.entity.Quotation;

import jakarta.transaction.Transactional;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Integer> {

    @Modifying
    @Transactional
    void deleteByQuotationIdIn(List<Integer> intQuotationIds);
}
