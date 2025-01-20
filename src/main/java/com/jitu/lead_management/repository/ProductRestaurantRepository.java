package com.jitu.lead_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jitu.lead_management.entity.ProductRestaurant;

@Repository
public interface ProductRestaurantRepository extends JpaRepository<ProductRestaurant, Integer> {

    @Query("SELECT pr.name FROM ProductRestaurant pr")
    List<String> fetchProductNames();

    void deleteByProductIdIn(List<Integer> intProductIds);
}
