package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUpdateVerificationTokenDataException extends AuthException {
    public InvalidUpdateVerificationTokenDataException(String message) {
        super(message);
    }
}
