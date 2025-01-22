package com.jitu.lead_management.model;

import java.util.Date;

import com.jitu.lead_management.entity.QuotationProductCar;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationProductCarViewModel {
    private String id;
    private String name;
    private int noOfCars;
    private int noOfDays;
    private Date fromDate;
    private Date toDate;
    private double price;

    public QuotationProductCarViewModel(QuotationProductCar quotationProductCar) {
        this.id = ProductUtils.generateProductId(quotationProductCar.getProductId());
        this.name = quotationProductCar.getName();
        this.noOfCars = quotationProductCar.getNoOfCars();
        this.noOfDays = quotationProductCar.getNoOfDays();
        this.fromDate = quotationProductCar.getFromDate();
        this.toDate = quotationProductCar.getToDate();
        this.price = quotationProductCar.getPrice();
    }
}
