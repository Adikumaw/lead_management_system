package com.jitu.lead_management.model;

import com.jitu.lead_management.entity.Itinerary;
import com.jitu.lead_management.utils.ItineraryUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryViewModel {
    private String itineraryId;
    private String templateName;
    private String subject;
    private String tripDetails;
    private String thankYouNote;
    private String termsAndConditions;

    public ItineraryViewModel(Itinerary itinerary) {
        this.itineraryId = ItineraryUtils.generateItineraryId(itinerary.getItineraryId());
        this.templateName = itinerary.getTemplateName();
        this.subject = itinerary.getSubject();
        this.tripDetails = itinerary.getTripDetails();
        this.thankYouNote = itinerary.getThankYouNote();
        this.termsAndConditions = itinerary.getTermsAndConditions();
    }
}
