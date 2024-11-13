package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeadException extends LeadManagementException {
    public LeadException(String message) {
        super(message);
    }
}
