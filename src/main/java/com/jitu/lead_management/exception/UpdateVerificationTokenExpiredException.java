package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateVerificationTokenExpiredException extends AuthException {
    public UpdateVerificationTokenExpiredException(String message) {
        super(message);
    }
}
