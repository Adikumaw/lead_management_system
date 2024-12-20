package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VerificationTokenNotFoundException extends AuthException {
    public VerificationTokenNotFoundException(String message) {
        super(message);
    }
}
