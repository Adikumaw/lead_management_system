package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUpdateVerificationTokenException extends AuthException {
    public InvalidUpdateVerificationTokenException(String message) {
        super(message);
    }
}
