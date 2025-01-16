package com.jitu.lead_management.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quotations")
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_id")
    private int quotationId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "currency")
    private String currency;
    @Column(name = "executive")
    private String executive;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_contact_no")
    private String clientContactNo;
    @Column(name = "client_email")
    private String clientEmail;
    @Column(name = "no_of_adults")
    private int noOfAdults;
    @Column(name = "no_of_chlidren")
    private int noOfChlidren;
    @Column(name = "destination")
    private String destination;
    @Column(name = "subject")
    private String subject;
    @Column(name = "trip_details")
    private String tripDetails;
    @Column(name = "thank_you_note")
    private String thankYouNote;
    @Column(name = "terms_and_conditions")
    private String termsAndConditions;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationProductCar> quotationProductCars;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationProductTrain> quotationProductTrains;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationProductPlane> quotationProductPlanes;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationProductHotel> quotationProductHotels;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationProductRestaurant> quotationProductRestaurants;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationProductPlace> quotationProductPlaces;

    @Column(name = "sub_total")
    private double subTotal;
    @Column(name = "discount")
    private double discount;
    @Column(name = "total")
    private double total;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "valid_upto")
    private Date validUpto;
}
