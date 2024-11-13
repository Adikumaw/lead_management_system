package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtException extends LeadManagementException {
    public JwtException(String message) {
        super(message);
    }
}
