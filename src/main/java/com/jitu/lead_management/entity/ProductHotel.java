package com.jitu.lead_management.entity;

import com.jitu.lead_management.model.ProductHotelModificationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_hotels")
public class ProductHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Column(name = "name")
    private String name;
    @Column(name = "room_type")
    private String roomType;
    @Column(name = "hourly_price")
    private double hourlyPrice;

    public ProductHotel(ProductHotelModificationModel productHotelModel) {
        this.name = productHotelModel.getName();
        this.roomType = productHotelModel.getRoomType();
        this.hourlyPrice = productHotelModel.getHourlyPrice();
    }
}
