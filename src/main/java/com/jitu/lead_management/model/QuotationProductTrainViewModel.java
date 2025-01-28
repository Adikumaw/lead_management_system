package com.jitu.lead_management.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jitu.lead_management.entity.QuotationProductTrain;
import com.jitu.lead_management.utils.CustomDateSerializer;
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
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date fromDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date toDate;
    private String from;
    private String to;
    private double price;

    public QuotationProductTrainViewModel(QuotationProductTrain quotationProductTrain) {
        this.id = ProductUtils.generateProductId(quotationProductTrain.getId());
        this.name = quotationProductTrain.getName();
        this.noOfChildren = quotationProductTrain.getNoOfChildren();
        this.noOfAdults = quotationProductTrain.getNoOfAdults();
        this.fromDate = adjustTimeZone(quotationProductTrain.getFromDate());
        this.toDate = adjustTimeZone(quotationProductTrain.getToDate());
        this.from = quotationProductTrain.getFrom();
        this.to = quotationProductTrain.getTo();
        this.price = quotationProductTrain.getPrice();
    }

    // Adjusts Date to Asia/Kolkata time zone without changing the actual time
    // (keeping it intact)
    private Date adjustTimeZone(Date date) {
        if (date == null) {
            return null;
        }
        ZonedDateTime utcTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
        ZonedDateTime kolkataTime = utcTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
        return Date.from(kolkataTime.toInstant()); // Convert back to Date object
    }
}
