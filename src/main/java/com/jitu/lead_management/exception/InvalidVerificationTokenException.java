package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidVerificationTokenException extends AuthException {
    public InvalidVerificationTokenException(String message) {
        super(message);
    }
}
