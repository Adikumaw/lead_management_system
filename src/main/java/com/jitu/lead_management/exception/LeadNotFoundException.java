package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeadNotFoundException extends LeadException {
    public LeadNotFoundException(String message) {
        super(message);
    }
}
