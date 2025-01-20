package com.jitu.lead_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jitu.lead_management.entity.ProductPlace;

@Repository
public interface ProductPlaceRepository extends JpaRepository<ProductPlace, Integer> {

    @Query("SELECT pp.name FROM ProductPlace pp")
    List<String> fetchProductNames();

    void deleteByProductIdIn(List<Integer> intProductIds);
}
