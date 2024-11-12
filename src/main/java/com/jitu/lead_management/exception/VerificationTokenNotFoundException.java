package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VerificationTokenNotFoundException extends UserException {
    public VerificationTokenNotFoundException(String message) {
        super(message);
    }
}
