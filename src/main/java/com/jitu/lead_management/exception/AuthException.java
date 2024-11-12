package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthException extends LeadManagementException {
    public AuthException(String message) {
        super(message);
    }
}
