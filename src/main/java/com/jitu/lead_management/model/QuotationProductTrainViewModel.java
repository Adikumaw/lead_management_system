package com.jitu.lead_management.model;

import java.util.Date;

import com.jitu.lead_management.entity.QuotationProductTrain;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationProductTrainViewModel {
    private String id;
    private String name;
    private int noOfChildren;
    private int noOfAdults;
    private Date fromDate;
    private Date toDate;
    private String from;
    private String to;
    private double price;

    public QuotationProductTrainViewModel(QuotationProductTrain quotationProductTrain) {
        this.id = ProductUtils.generateProductId(quotationProductTrain.getId());
        this.name = quotationProductTrain.getName();
        this.noOfChildren = quotationProductTrain.getNoOfChildren();
        this.noOfAdults = quotationProductTrain.getNoOfAdults();
        this.fromDate = quotationProductTrain.getFromDate();
        this.toDate = quotationProductTrain.getToDate();
        this.from = quotationProductTrain.getFrom();
        this.to = quotationProductTrain.getTo();
        this.price = quotationProductTrain.getPrice();
    }
}
