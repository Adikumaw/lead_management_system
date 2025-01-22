package com.jitu.lead_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductHotelModificationModel {
    private String name;
    @JsonProperty("room_type")
    private String roomType;
    @JsonProperty("hourly_price")
    private double hourlyPrice;
}
