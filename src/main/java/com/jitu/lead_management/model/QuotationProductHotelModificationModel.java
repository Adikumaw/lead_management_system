package com.jitu.lead_management.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationProductHotelModificationModel {
    private String id;
    private String name;
    @JsonProperty("room_type")
    private String roomType;
    @JsonProperty("no_of_rooms")
    private int noOfRooms;
    @JsonProperty("no_of_days")
    private int noOfDays;
    @JsonProperty("check_in_date")
    private Date checkInDate;
    @JsonProperty("check_out_date")
    private Date checkOutDate;
    private double price;
}
