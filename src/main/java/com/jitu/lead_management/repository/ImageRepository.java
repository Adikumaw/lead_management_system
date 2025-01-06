package com.jitu.lead_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jitu.lead_management.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
