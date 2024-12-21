package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VerificationFailedException extends AuthException {
    public VerificationFailedException(String message) {
        super(message);
    }
}
