package com.jitu.lead_management.model;

import com.jitu.lead_management.entity.ProductRestaurant;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRestaurantViewModel {
    private String productId;
    private String name;
    private double avgPerChildPrice;
    private double avgPerAdultPrice;

    public ProductRestaurantViewModel(ProductRestaurant productRestaurant) {
        this.productId = ProductUtils.generateProductId(productRestaurant.getProductId());
        this.name = productRestaurant.getName();
        this.avgPerChildPrice = productRestaurant.getAvgPerChildPrice();
        this.avgPerAdultPrice = productRestaurant.getAvgPerAdultPrice();
    }
}
