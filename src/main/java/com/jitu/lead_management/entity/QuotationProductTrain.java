package com.jitu.lead_management.entity;

import java.util.Date;

import com.jitu.lead_management.model.QuotationProductTrainModificationModel;

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
@Table(name = "quotation_product_trains")
public class QuotationProductTrain {
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
    @Column(name = "from_station")
    private String from;
    @Column(name = "to_station")
    private String to;
    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quotation_id", nullable = false)
    private Quotation quotation;

    public QuotationProductTrain(QuotationProductTrainModificationModel QuotationProductTrainModel) {
        this.name = QuotationProductTrainModel.getName();
        this.noOfChildren = QuotationProductTrainModel.getNoOfChildren();
        this.noOfAdults = QuotationProductTrainModel.getNoOfAdults();
        this.fromDate = QuotationProductTrainModel.getFromDate();
        this.toDate = QuotationProductTrainModel.getToDate();
        this.from = QuotationProductTrainModel.getFrom();
        this.to = QuotationProductTrainModel.getTo();
        this.price = QuotationProductTrainModel.getPrice();
    }
}
