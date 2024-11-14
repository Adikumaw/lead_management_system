package com.jitu.lead_management.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jitu.lead_management.entity.Lead;
import com.jitu.lead_management.utils.CustomDateSerializer;
import com.jitu.lead_management.utils.LeadUtils;

import lombok.Data;

@Data
public class LeadViewModel {
    private String leadId;
    private String stage;
    private String clientName;
    private String clientContactNo;
    private String clientEmailId;

    // Store Date type for followUp
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date followUp;

    private String executive;
    private String status;
    private String enquiryType;
    private String packageName;
    private String destination;
    private int noOfAdults;
    private Double budgetPerAdult;
    private int noOfChildren;
    private Double budgetPerChild;

    // Date fields
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date plannedTravelDate;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdAt;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updatedAt;

    public LeadViewModel(Lead lead) {
        this.leadId = LeadUtils.generateLeadId(lead.getLeadId(), lead.getUserId());
        this.stage = lead.getStage();
        this.clientName = lead.getClientName();
        this.clientContactNo = lead.getClientContactNo();
        this.clientEmailId = lead.getClientEmailId();

        // Adjust time zone but keep as Date
        this.followUp = adjustTimeZone(lead.getFollowUp());
        this.executive = lead.getExecutive();
        this.status = lead.getStatus();
        this.enquiryType = lead.getEnquiryType();
        this.packageName = lead.getPackageName();
        this.destination = lead.getDestination();
        this.noOfAdults = lead.getNoOfAdults();
        this.budgetPerAdult = lead.getBudgetPerAdult();
        this.noOfChildren = lead.getNoOfChildren();
        this.budgetPerChild = lead.getBudgetPerChild();

        // Adjust time zone for other Date fields as well
        this.plannedTravelDate = adjustTimeZone(lead.getPlannedTravelDate());
        this.createdAt = adjustTimeZone(lead.getCreatedAt());
        this.updatedAt = adjustTimeZone(lead.getUpdatedAt());
    }

    // Adjusts Date to Asia/Kolkata time zone without changing the actual time
    // (keeping it intact)
    private Date adjustTimeZone(Date date) {
        if (date == null) {
            return null;
        }
        ZonedDateTime utcTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
        ZonedDateTime kolkataTime = utcTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
        return Date.from(kolkataTime.toInstant()); // Convert back to Date object
    }
}
