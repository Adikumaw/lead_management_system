package com.jitu.lead_management.entity;

import com.jitu.lead_management.model.ProductRestaurantModificationModel;

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
@Table(name = "product_restaurants")
public class ProductRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Column(name = "name")
    private String name;
    @Column(name = "avg_per_child_price")
    private double avgPerChildPrice;
    @Column(name = "avg_per_adult_price")
    private double avgPerAdultPrice;

    public ProductRestaurant(ProductRestaurantModificationModel productRestaurantModel) {
        this.name = productRestaurantModel.getName();
        this.avgPerChildPrice = productRestaurantModel.getAvgPerChildPrice();
        this.avgPerAdultPrice = productRestaurantModel.getAvgPerAdultPrice();
    }
}
