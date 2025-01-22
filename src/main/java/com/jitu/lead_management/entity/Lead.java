package com.jitu.lead_management.entity;

import java.util.Date;

import com.jitu.lead_management.model.LeadModificationModel;

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
    private int userId;
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
    private Date plannedTravelDate;
    @Column(name = "destination")
    private String destination;
    @Column(name = "no_of_adults")
    private int noOfAdults;
    @Column(name = "budget_per_adult")
    private Double budgetPerAdult;
    @Column(name = "no_of_children")
    private int noOfChildren;
    @Column(name = "budget_per_child")
    private Double budgetPerChild;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public Lead(int userId, LeadModificationModel lead) {
        this.userId = userId;
        this.stage = lead.getStage();
        this.clientName = lead.getClientName();
        this.clientContactNo = lead.getClientContactNo();
        this.clientEmailId = lead.getClientEmailId();
        this.followUp = lead.getFollowUp();
        this.executive = lead.getExecutive();
        this.status = lead.getStatus();
        this.enquiryType = lead.getEnquiryType();
        this.packageName = lead.getPackageName();
        this.plannedTravelDate = lead.getPlannedTravelDate();
        this.destination = lead.getDestination();
        this.noOfAdults = lead.getNoOfAdults();
        this.budgetPerAdult = lead.getBudgetPerAdult();
        this.noOfChildren = lead.getNoOfChildren();
        this.budgetPerChild = lead.getBudgetPerChild();
        this.createdAt = new Date();
        this.updatedAt = createdAt;
    }

}
