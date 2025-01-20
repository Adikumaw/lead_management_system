package com.jitu.lead_management.entity;

import java.util.Date;

import com.jitu.lead_management.model.QuotationProductHotelModificationModel;
import com.jitu.lead_management.utils.ProductUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quotation_product_hotels")
public class QuotationProductHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    // @Column(name = "quotation_id", insertable = false, updatable = false)
    // private int quotationId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "name")
    private String name;
    @Column(name = "room_type")
    private String roomType;
    @Column(name = "no_of_rooms")
    private int noOfRooms;
    @Column(name = "no_of_days")
    private int noOfDays;
    @Column(name = "check_in_date")
    private Date checkInDate;
    @Column(name = "check_out_date")
    private Date checkOutDate;
    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quotation_id", nullable = false)
    private Quotation quotation;

    public QuotationProductHotel(QuotationProductHotelModificationModel quotationProductHotelModel) {
        this.productId = ProductUtils
                .resolveProductId(quotationProductHotelModel.getId());
        this.name = quotationProductHotelModel.getName();
        this.roomType = quotationProductHotelModel.getRoomType();
        this.noOfRooms = quotationProductHotelModel.getNoOfRooms();
        this.noOfDays = quotationProductHotelModel.getNoOfDays();
        this.checkInDate = quotationProductHotelModel.getCheckInDate();
        this.checkOutDate = quotationProductHotelModel.getCheckOutDate();
        this.price = quotationProductHotelModel.getPrice();
    }
}
