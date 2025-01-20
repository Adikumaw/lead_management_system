package com.jitu.lead_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPlaceModificationModel {
    private String name;
    @JsonProperty("per_child_price")
    private double perChildPrice;
    @JsonProperty("per_adult_price")
    private double perAdultPrice;
}
