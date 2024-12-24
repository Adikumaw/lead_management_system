package com.jitu.lead_management.entity;

import com.jitu.lead_management.model.ItineraryModificationModel;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itinerary")
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerary_id")
    private int itineraryId;
    @Column(name = "template_name")
    private String templateName;
    @Column(name = "subject")
    private String subject;
    @Column(name = "trip_details")
    private String tripDetails;
    @Column(name = "thank_you_note")
    private String thankYouNote;
    @Column(name = "terms_and_conditions")
    private String termsAndConditions;

    public Itinerary(ItineraryModificationModel itineraryModel) {
        this.templateName = itineraryModel.getTemplateName();
        this.subject = itineraryModel.getSubject();
        this.tripDetails = itineraryModel.getTripDetails();
        this.thankYouNote = itineraryModel.getThankYouNote();
        this.termsAndConditions = itineraryModel.getTermsAndConditions();
    }
}
