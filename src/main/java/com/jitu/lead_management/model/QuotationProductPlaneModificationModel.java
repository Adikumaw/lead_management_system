package com.jitu.lead_management.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationProductPlaneModificationModel {
    private String id;
    private String name;
    @JsonProperty("no_of_children")
    private int noOfChildren;
    @JsonProperty("no_of_adults")
    private int noOfAdults;
    @JsonProperty("from_date")
    private Date fromDate;
    @JsonProperty("to_date")
    private Date toDate;
    private String from;
    private String to;
    private double price;
}
