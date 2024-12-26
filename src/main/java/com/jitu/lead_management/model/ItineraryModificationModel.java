package com.jitu.lead_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryModificationModel {
    @JsonProperty("template_name")
    private String templateName;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("trip_details")
    private String tripDetails;
    @JsonProperty("thank_you_note")
    private String thankYouNote;
    @JsonProperty("terms_and_conditions")
    private String termsAndConditions;
}
