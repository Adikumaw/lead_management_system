package com.jitu.lead_management.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jitu.lead_management.entity.QuotationProductCar;
import com.jitu.lead_management.utils.CustomDateSerializer;
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
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date fromDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date toDate;
    private double price;

    public QuotationProductCarViewModel(QuotationProductCar quotationProductCar) {
        this.id = ProductUtils.generateProductId(quotationProductCar.getProductId());
        this.name = quotationProductCar.getName();
        this.noOfCars = quotationProductCar.getNoOfCars();
        this.noOfDays = quotationProductCar.getNoOfDays();
        this.fromDate = adjustTimeZone(quotationProductCar.getFromDate());
        this.toDate = adjustTimeZone(quotationProductCar.getToDate());
        this.price = quotationProductCar.getPrice();
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
