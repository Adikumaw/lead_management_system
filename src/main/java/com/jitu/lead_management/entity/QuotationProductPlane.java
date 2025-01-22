package com.jitu.lead_management.entity;

import java.util.Date;

import com.jitu.lead_management.model.QuotationProductPlaneModificationModel;

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
@Table(name = "quotation_product_planes")
public class QuotationProductPlane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    // @Column(name = "quotation_id", insertable = false, updatable = false)
    // private int quotationId;
    @Column(name = "name")
    private String name;
    @Column(name = "no_of_children")
    private int noOfChildren;
    @Column(name = "no_of_adults")
    private int noOfAdults;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;
    @Column(name = "from_airport")
    private String from;
    @Column(name = "to_airport")
    private String to;
    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quotation_id", nullable = false)
    private Quotation quotation;

    public QuotationProductPlane(QuotationProductPlaneModificationModel QuotationProductPlaneModel) {
        this.name = QuotationProductPlaneModel.getName();
        this.noOfChildren = QuotationProductPlaneModel.getNoOfChildren();
        this.noOfAdults = QuotationProductPlaneModel.getNoOfAdults();
        this.fromDate = QuotationProductPlaneModel.getFromDate();
        this.toDate = QuotationProductPlaneModel.getToDate();
        this.from = QuotationProductPlaneModel.getFrom();
        this.to = QuotationProductPlaneModel.getTo();
        this.price = QuotationProductPlaneModel.getPrice();
    }
}
