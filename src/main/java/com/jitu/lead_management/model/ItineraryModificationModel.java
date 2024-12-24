package com.jitu.lead_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryModificationModel {
    private String templateName;
    private String subject;
    private String tripDetails;
    private String thankYouNote;
    private String termsAndConditions;
}
