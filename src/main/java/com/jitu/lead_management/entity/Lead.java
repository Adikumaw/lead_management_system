package com.jitu.lead_management.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_id")
    private int leadId;
    @Column(name = "user_id")
    private String userId;
    // allowed values
    // enum('hot','warm','cold','not interested','not answer','proposal','meeting
    // fixed','converted to clients','meeting completed','active','converted to hot
    // deals')
    @Column(name = "stage")
    private String stage;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_contact_no")
    private String clientContactNo;
    @Column(name = "client_email_id")
    private String clientEmailId;
    @Column(name = "follow_up")
    private Date followUp;
    @Column(name = "executive")
    private String executive;
    // allowed values
    // enum('unattended','blocked','proposal sent','spoke','meeting
    // fixed','met','closed','lost','active','converted to hot deals','converted to
    // customer','converted to support ticket')
    @Column(name = "status")
    private String status;
    // allowed values
    // enum('flight booking','hotel booking','sight seeing','transport','other')
    @Column(name = "enquiry_type")
    private String enquiryType;
    @Column(name = "package")
    private String packageName;
    @Column(name = "planned_travel_date")
    private String plannedTravelDate;
    @Column(name = "destination")
    private String destination;
    @Column(name = "no_of_adults")
    private int noOfAdults;
    @Column(name = "budget_per_adult")
    private Double budgetPerAdult;
    @Column(name = "no_of_children")
    private int noOfChildren;
    @Column(name = "budget_per_children")
    private Double budgetPerChild;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public Lead(String userId, String stage, String clientName, String clientContactNo, String clientEmailId,
            Date followUp, String executive, String status, String enquiryType, String packageName,
            String plannedTravelDate, String destination, int noOfAdults, Double budgetPerAdult, int noOfChildren,
            Double budgetPerChild) {
        this.userId = userId;
        this.stage = stage;
        this.clientName = clientName;
        this.clientContactNo = clientContactNo;
        this.clientEmailId = clientEmailId;
        this.followUp = followUp;
        this.executive = executive;
        this.status = status;
        this.enquiryType = enquiryType;
        this.packageName = packageName;
        this.plannedTravelDate = plannedTravelDate;
        this.destination = destination;
        this.noOfAdults = noOfAdults;
        this.budgetPerAdult = budgetPerAdult;
        this.noOfChildren = noOfChildren;
        this.budgetPerChild = budgetPerChild;
        this.createdAt = new Date();
        this.updatedAt = createdAt;
    }

}
