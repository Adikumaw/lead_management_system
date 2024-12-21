package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VerificationTokenExpiredException extends AuthException {
    public VerificationTokenExpiredException(String message) {
        super(message);
    }
}
