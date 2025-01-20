package com.jitu.lead_management.entity;

import com.jitu.lead_management.model.ProductPlaceModificationModel;

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
@Table(name = "product_places")
public class ProductPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Column(name = "name")
    private String name;
    @Column(name = "per_child_price")
    private double perChildPrice;
    @Column(name = "per_adult_price")
    private double perAdultPrice;

    public ProductPlace(ProductPlaceModificationModel productPlaceModel) {
        this.name = productPlaceModel.getName();
        this.perChildPrice = productPlaceModel.getPerChildPrice();
        this.perAdultPrice = productPlaceModel.getPerAdultPrice();
    }
}
