package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateVerificationFailedException extends AuthException {
    public UpdateVerificationFailedException(String message) {
        super(message);
    }
}
