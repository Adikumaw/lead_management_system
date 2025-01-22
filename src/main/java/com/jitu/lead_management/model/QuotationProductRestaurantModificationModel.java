package com.jitu.lead_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationProductRestaurantModificationModel {
    private String id;
    private String name;
    @JsonProperty("no_of_children")
    private int noOfChildren;
    @JsonProperty("no_of_adults")
    private int noOfAdults;
    @JsonProperty("avg_price")
    private double avgPrice;
}
