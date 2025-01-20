package com.jitu.lead_management.model;

import com.jitu.lead_management.entity.ProductPlace;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPlaceViewModel {
    private String productId;
    private String name;
    private double perChildPrice;
    private double perAdultPrice;

    public ProductPlaceViewModel(ProductPlace productPlace) {
        this.productId = ProductUtils.generateProductId(productPlace.getProductId());
        this.name = productPlace.getName();
        this.perChildPrice = productPlace.getPerChildPrice();
        this.perAdultPrice = productPlace.getPerAdultPrice();
    }
}
