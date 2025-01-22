package com.jitu.lead_management.model;

import java.util.Date;

import com.jitu.lead_management.entity.QuotationProductHotel;
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
    private Date checkInDate;
    private Date checkOutDate;
    private double price;

    public QuotationProductHotelViewModel(QuotationProductHotel quotationProductHotel) {
        this.id = ProductUtils.generateProductId(quotationProductHotel.getProductId());
        this.name = quotationProductHotel.getName();
        this.roomType = quotationProductHotel.getRoomType();
        this.noOfRooms = quotationProductHotel.getNoOfRooms();
        this.noOfDays = quotationProductHotel.getNoOfDays();
        this.checkInDate = quotationProductHotel.getCheckInDate();
        this.checkOutDate = quotationProductHotel.getCheckOutDate();
        this.price = quotationProductHotel.getPrice();
    }
}
