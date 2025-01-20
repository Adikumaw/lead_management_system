package com.jitu.lead_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jitu.lead_management.entity.ProductHotel;

@Repository
public interface ProductHotelRepository extends JpaRepository<ProductHotel, Integer> {

    @Query("SELECT ph.name FROM ProductHotel ph")
    List<String> fetchProductNames();

    void deleteByProductIdIn(List<Integer> intProductIds);
}
