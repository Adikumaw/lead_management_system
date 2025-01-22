package com.jitu.lead_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRestaurantModificationModel {
    private String name;
    @JsonProperty("avg_per_child_price")
    private double avgPerChildPrice;
    @JsonProperty("avg_per_adult_price")
    private double avgPerAdultPrice;
}
