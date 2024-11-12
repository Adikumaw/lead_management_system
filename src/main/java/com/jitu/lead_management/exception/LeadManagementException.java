package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeadManagementException extends RuntimeException {
    public LeadManagementException(String message) {
        super(message);
    }
}
