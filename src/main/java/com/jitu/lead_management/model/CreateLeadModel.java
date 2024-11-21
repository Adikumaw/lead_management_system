package com.jitu.lead_management.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Data;

@Data
public class CreateLeadModel {
    private String stage;
    @JsonProperty("client_name")
    private String clientName;
    @JsonProperty("client_contact_no")
    private String clientContactNo;
    @JsonProperty("client_email_id")
    private String clientEmailId;
    @JsonProperty("follow_up")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Kolkata") // set to indian time zone
    private Date followUp;
    private String executive;
    private String status;
    @JsonProperty("enquiry_type")
    private String enquiryType;
    @JsonProperty("package_name")
    private String packageName;
    @JsonProperty("planned_travel_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Kolkata")
    private Date plannedTravelDate;
    private String destination;
    @JsonProperty("no_of_adults")
    private Integer noOfAdults;
    @JsonProperty("budget_per_adult")
    private Double budgetPerAdult;
    @JsonProperty("no_of_children")
    private Integer noOfChildren;
    @JsonProperty("budget_per_child")
    private Double budgetPerChild;

    @JsonSetter("")
    public void setNoOfChildren(Integer noOfChildren) {
        this.noOfChildren = noOfChildren == null ? 0 : noOfChildren;
    }

    @JsonSetter("")
    public void setNoOfAdults(Integer noOfAdults) {
        this.noOfAdults = noOfAdults == null ? 0 : noOfAdults;
    }

}
