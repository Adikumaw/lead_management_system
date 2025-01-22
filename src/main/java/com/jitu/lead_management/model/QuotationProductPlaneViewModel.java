package com.jitu.lead_management.model;

import java.util.Date;

import com.jitu.lead_management.entity.QuotationProductPlane;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationProductPlaneViewModel {
    private String id;
    private String name;
    private int noOfChildren;
    private int noOfAdults;
    private Date fromDate;
    private Date toDate;
    private String from;
    private String to;
    private double price;

    public QuotationProductPlaneViewModel(QuotationProductPlane quotationProductPlane) {
        this.id = ProductUtils.generateProductId(quotationProductPlane.getId());
        this.name = quotationProductPlane.getName();
        this.noOfChildren = quotationProductPlane.getNoOfChildren();
        this.noOfAdults = quotationProductPlane.getNoOfAdults();
        this.fromDate = quotationProductPlane.getFromDate();
        this.toDate = quotationProductPlane.getToDate();
        this.from = quotationProductPlane.getFrom();
        this.to = quotationProductPlane.getTo();
        this.price = quotationProductPlane.getPrice();
    }
}
