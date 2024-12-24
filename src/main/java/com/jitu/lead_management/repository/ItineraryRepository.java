package com.jitu.lead_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jitu.lead_management.entity.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {

    void deleteByItineraryIdIn(List<Integer> intItineraryIds);

    @Query("SELECT i.templateName FROM Itinerary i")
    List<String> fetchTemplateNames();

}
