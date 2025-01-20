package com.jitu.lead_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jitu.lead_management.entity.ProductCar;

@Repository
public interface ProductCarRepository extends JpaRepository<ProductCar, Integer> {

    @Query("SELECT pc.name FROM ProductCar pc")
    List<String> fetchProductNames();

    void deleteByProductIdIn(List<Integer> intProductIds);
}
