package com.jitu.lead_management.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_metadata")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "download_url")
    private String downloadUrl;
    @Column(name = "created_at")
    private Date createdAt;

    public Image(String imageName, String imagePath, String downloadUrl) {
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.downloadUrl = downloadUrl;
        this.createdAt = new Date();
    }
}
