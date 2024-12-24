package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItineraryAndQuotationException extends LeadManagementException {
    public ItineraryAndQuotationException(String message) {
        super(message);
    }
}
