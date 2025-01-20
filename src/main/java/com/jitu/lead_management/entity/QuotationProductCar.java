package com.jitu.lead_management.entity;

import java.util.Date;

import com.jitu.lead_management.model.QuotationProductCarModificationModel;
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
@Table(name = "quotation_product_cars")
public class QuotationProductCar {

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
    @Column(name = "no_of_cars")
    private int noOfCars;
    @Column(name = "no_of_days")
    private int noOfDays;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;
    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quotation_id", nullable = false)
    private Quotation quotation;

    public QuotationProductCar(QuotationProductCarModificationModel quotationProductCarModel) {
        this.productId = ProductUtils.resolveProductId(quotationProductCarModel.getId());
        this.name = quotationProductCarModel.getName();
        this.noOfCars = quotationProductCarModel.getNoOfCars();
        this.noOfDays = quotationProductCarModel.getNoOfDays();
        this.fromDate = quotationProductCarModel.getFromDate();
        this.toDate = quotationProductCarModel.getToDate();
        this.price = quotationProductCarModel.getPrice();
    }
}
