package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QuotationNotFoundException extends QuotationException {
    public QuotationNotFoundException(String message) {
        super(message);
    }
}
