package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItineraryNotFoundException extends ItineraryAndQuotationException {
    public ItineraryNotFoundException(String message) {
        super(message);
    }
}
