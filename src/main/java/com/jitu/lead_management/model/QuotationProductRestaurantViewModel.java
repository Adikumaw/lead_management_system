package com.jitu.lead_management.model;

import com.jitu.lead_management.entity.QuotationProductRestaurant;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationProductRestaurantViewModel {
    private String id;
    private String name;
    private double avgPerChildPrice;
    private double avgPerAdultPrice;
    private int noOfChildren;
    private int noOfAdults;
    private double avgPrice;

    public QuotationProductRestaurantViewModel(QuotationProductRestaurant quotationProductRestaurant) {
        this.id = ProductUtils.generateProductId(quotationProductRestaurant.getProductId());
        this.name = quotationProductRestaurant.getName();
        this.avgPerChildPrice = quotationProductRestaurant.getAvgPerChildPrice();
        this.avgPerAdultPrice = quotationProductRestaurant.getAvgPerAdultPrice();
        this.noOfChildren = quotationProductRestaurant.getNoOfChildren();
        this.noOfAdults = quotationProductRestaurant.getNoOfAdults();
        this.avgPrice = quotationProductRestaurant.getAvgPrice();
    }
}
