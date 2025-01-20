package com.jitu.lead_management.entity;

import com.jitu.lead_management.model.QuotationProductPlaceModificationModel;
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
@Table(name = "quotation_product_places")
public class QuotationProductPlace {
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
    @Column(name = "no_of_children")
    private int noOfChildren;
    @Column(name = "no_of_adults")
    private int noOfAdults;
    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quotation_id", nullable = false)
    private Quotation quotation;

    public QuotationProductPlace(QuotationProductPlaceModificationModel QuotationProductPlaceModel) {
        this.productId = ProductUtils.resolveProductId(QuotationProductPlaceModel.getId());
        this.name = QuotationProductPlaceModel.getName();
        this.noOfChildren = QuotationProductPlaceModel.getNoOfChildren();
        this.noOfAdults = QuotationProductPlaceModel.getNoOfAdults();
        this.price = QuotationProductPlaceModel.getPrice();
    }
}
