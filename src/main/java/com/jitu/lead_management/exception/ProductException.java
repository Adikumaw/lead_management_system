package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductException extends LeadManagementException {
    public ProductException(String message) {
        super(message);
    }
}
