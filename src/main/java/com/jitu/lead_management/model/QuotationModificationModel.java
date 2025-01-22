package com.jitu.lead_management.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationModificationModel {
    private String currency;
    private String executive;
    @JsonProperty("client_name")
    private String clientName;
    @JsonProperty("client_contact_no")
    private String clientContactNo;
    @JsonProperty("client_email")
    private String clientEmail;
    @JsonProperty("no_of_adults")
    private int noOfAdults;
    @JsonProperty("no_of_children")
    private int noOfChildren;
    private String destination;
    private String subject;
    @JsonProperty("trip_details")
    private String tripDetails;
    @JsonProperty("thank_you_note")
    private String thankYouNote;
    @JsonProperty("terms_and_conditions")
    private String termsAndConditions;
    @JsonProperty("quotation_product_cars")
    private List<QuotationProductCarModificationModel> quotationProductCarModels;
    @JsonProperty("quotation_product_trains")
    private List<QuotationProductTrainModificationModel> quotationProductTrainModels;
    @JsonProperty("quotation_product_planes")
    private List<QuotationProductPlaneModificationModel> quotationProductPlaneModels;
    @JsonProperty("quotation_product_hotels")
    private List<QuotationProductHotelModificationModel> quotationProductHotelModels;
    @JsonProperty("quotation_product_restaurants")
    private List<QuotationProductRestaurantModificationModel> quotationProductRestaurantModels;
    @JsonProperty("quotation_product_places")
    private List<QuotationProductPlaceModificationModel> quotationProductPlaceModels;
    @JsonProperty("sub_total")
    private double subTotal;
    private double discount;
    private double total;
    @JsonProperty("valid_upto")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Kolkata") // set to indian time zone
    private Date validUpto;

    @JsonSetter("")
    public void setNoOfChildren(Integer noOfChildren) {
        this.noOfChildren = noOfChildren == null ? 0 : noOfChildren;
    }

    @JsonSetter("")
    public void setNoOfAdults(Integer noOfAdults) {
        this.noOfAdults = noOfAdults == null ? 0 : noOfAdults;
    }
}
