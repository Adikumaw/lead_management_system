package com.jitu.lead_management.model;

import com.jitu.lead_management.entity.ProductCar;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCarViewModel {
    private String productId;
    private String name;
    private double hourlyPrice;

    public ProductCarViewModel(ProductCar productCar) {
        this.productId = ProductUtils.generateProductId(productCar.getProductId());
        this.name = productCar.getName();
        this.hourlyPrice = productCar.getHourlyPrice();
    }
}
