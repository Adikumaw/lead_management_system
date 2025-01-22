package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QuotationException extends LeadManagementException {
    public QuotationException(String message) {
        super(message);
    }
}
