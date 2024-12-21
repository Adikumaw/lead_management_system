package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateVerificationTokenNotFoundException extends AuthException {
    public UpdateVerificationTokenNotFoundException(String message) {
        super(message);
    }
}
