package com.jitu.lead_management.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationProductCarModificationModel {
    private String id;
    private String name;
    @JsonProperty("no_of_cars")
    private int noOfCars;
    @JsonProperty("no_of_days")
    private int noOfDays;
    @JsonProperty("from_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
    private Date fromDate;
    @JsonProperty("to_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
    private Date toDate;
    private double price;

}
