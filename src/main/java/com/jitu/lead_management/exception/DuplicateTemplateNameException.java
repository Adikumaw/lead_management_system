package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicateTemplateNameException extends ItineraryAndQuotationException {
    public DuplicateTemplateNameException(String message) {
        super(message);
    }
}
