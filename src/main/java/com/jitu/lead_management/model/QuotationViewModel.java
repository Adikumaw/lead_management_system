package com.jitu.lead_management.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.jitu.lead_management.entity.Quotation;
import com.jitu.lead_management.utils.QuotationUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationViewModel {

    private String quotationId;
    private String currency;
    private String executive;
    private String clientName;
    private String clientContactNo;
    private String clientEmail;
    private int noOfAdults;
    private int noOfChildren;
    private String destination;
    private String subject;
    private String tripDetails;
    private String thankYouNote;
    private String termsAndConditions;

    private List<QuotationProductCarViewModel> quotationProductCars;

    private List<QuotationProductTrainViewModel> quotationProductTrains;

    private List<QuotationProductPlaneViewModel> quotationProductPlanes;

    private List<QuotationProductHotelViewModel> quotationProductHotels;

    private List<QuotationProductRestaurantViewModel> quotationProductRestaurants;

    private List<QuotationProductPlaceViewModel> quotationProductPlaces;

    private double subTotal;
    private double discount;
    private double total;
    private Date createdAt;
    private Date validUpto;

    public QuotationViewModel(Quotation quotation) {
        this.quotationId = QuotationUtils.generateQuotationId(quotation.getQuotationId(), quotation.getUserId());
        this.currency = quotation.getCurrency();
        this.executive = quotation.getExecutive();
        this.clientName = quotation.getClientName();
        this.clientContactNo = quotation.getClientContactNo();
        this.clientEmail = quotation.getClientEmail();
        this.noOfAdults = quotation.getNoOfAdults();
        this.noOfChildren = quotation.getNoOfChildren();
        this.destination = quotation.getDestination();
        this.subject = quotation.getSubject();
        this.tripDetails = quotation.getTripDetails();
        this.thankYouNote = quotation.getThankYouNote();
        this.termsAndConditions = quotation.getTermsAndConditions();
        this.subTotal = quotation.getSubTotal();
        this.discount = quotation.getDiscount();
        this.total = quotation.getTotal();
        this.createdAt = quotation.getCreatedAt();
        this.validUpto = quotation.getValidUpto();
        this.quotationProductCars = quotation.getQuotationProductCars().stream()
                .map(QuotationProductCarViewModel::new).collect(Collectors.toList());
        this.quotationProductHotels = quotation.getQuotationProductHotels().stream()
                .map(QuotationProductHotelViewModel::new).collect(Collectors.toList());
        this.quotationProductPlaces = quotation.getQuotationProductPlaces().stream()
                .map(QuotationProductPlaceViewModel::new).collect(Collectors.toList());
        this.quotationProductPlanes = quotation.getQuotationProductPlanes().stream()
                .map(QuotationProductPlaneViewModel::new).collect(Collectors.toList());
        this.quotationProductRestaurants = quotation.getQuotationProductRestaurants().stream()
                .map(QuotationProductRestaurantViewModel::new).collect(Collectors.toList());
        this.quotationProductTrains = quotation.getQuotationProductTrains().stream()
                .map(QuotationProductTrainViewModel::new).collect(Collectors.toList());
    }
}
