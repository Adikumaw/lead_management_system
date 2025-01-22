package com.jitu.lead_management.model;

import com.jitu.lead_management.entity.QuotationProductPlace;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationProductPlaceViewModel {
    private String id;
    private String name;
    private double perChildPrice;
    private double perAdultPrice;
    private int noOfChildren;
    private int noOfAdults;
    private double price;

    public QuotationProductPlaceViewModel(QuotationProductPlace quotationProductPlace) {
        this.id = ProductUtils.generateProductId(quotationProductPlace.getProductId());
        this.name = quotationProductPlace.getName();
        this.perChildPrice = quotationProductPlace.getPerChildPrice();
        this.perAdultPrice = quotationProductPlace.getPerAdultPrice();
        this.noOfChildren = quotationProductPlace.getNoOfChildren();
        this.noOfAdults = quotationProductPlace.getNoOfAdults();
        this.price = quotationProductPlace.getPrice();
    }
}
