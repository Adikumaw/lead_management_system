package com.jitu.lead_management.model;

import com.jitu.lead_management.entity.ProductHotel;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductHotelViewModel {
    private String productId;
    private String name;
    private String roomType;
    private double hourlyPrice;

    public ProductHotelViewModel(ProductHotel productHotel) {
        this.productId = ProductUtils.generateProductId(productHotel.getProductId());
        this.name = productHotel.getName();
        this.roomType = productHotel.getRoomType();
        this.hourlyPrice = productHotel.getHourlyPrice();
    }
}
