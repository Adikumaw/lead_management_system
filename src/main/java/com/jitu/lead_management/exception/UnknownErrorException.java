package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnknownErrorException extends LeadManagementException {
    public UnknownErrorException(String message) {
        super(message);
    }
}
