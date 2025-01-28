package com.jitu.lead_management.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jitu.lead_management.entity.QuotationProductHotel;
import com.jitu.lead_management.utils.CustomDateSerializer;
import com.jitu.lead_management.utils.ProductUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationProductHotelViewModel {
    private String id;
    private String name;
    private String roomType;
    private int noOfRooms;
    private int noOfDays;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date checkInDate;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date checkOutDate;
    private double price;

    public QuotationProductHotelViewModel(QuotationProductHotel quotationProductHotel) {
        this.id = ProductUtils.generateProductId(quotationProductHotel.getProductId());
        this.name = quotationProductHotel.getName();
        this.roomType = quotationProductHotel.getRoomType();
        this.noOfRooms = quotationProductHotel.getNoOfRooms();
        this.noOfDays = quotationProductHotel.getNoOfDays();
        this.checkInDate = adjustTimeZone(quotationProductHotel.getCheckInDate());
        this.checkOutDate = adjustTimeZone(quotationProductHotel.getCheckOutDate());
        this.price = quotationProductHotel.getPrice();
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
