package com.jitu.lead_management.entity;

import com.jitu.lead_management.model.QuotationProductRestaurantModificationModel;
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
@Table(name = "quotation_product_restaurants")
public class QuotationProductRestaurant {
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
    @Column(name = "avg_per_child_price")
    private double avgPerChildPrice;
    @Column(name = "avg_per_adult_price")
    private double avgPerAdultPrice;
    @Column(name = "no_of_children")
    private int noOfChildren;
    @Column(name = "no_of_adults")
    private int noOfAdults;
    @Column(name = "avg_price")
    private double avgPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quotation_id", nullable = false)
    private Quotation quotation;

    public QuotationProductRestaurant(QuotationProductRestaurantModificationModel QuotationProductRestaurantModel) {
        this.productId = ProductUtils.resolveProductId(QuotationProductRestaurantModel.getId());
        this.name = QuotationProductRestaurantModel.getName();
        this.avgPerChildPrice = QuotationProductRestaurantModel.getAvgPerChildPrice();
        this.avgPerAdultPrice = QuotationProductRestaurantModel.getAvgPerAdultPrice();
        this.noOfChildren = QuotationProductRestaurantModel.getNoOfChildren();
        this.noOfAdults = QuotationProductRestaurantModel.getNoOfAdults();
        this.avgPrice = QuotationProductRestaurantModel.getAvgPrice();
    }
}
