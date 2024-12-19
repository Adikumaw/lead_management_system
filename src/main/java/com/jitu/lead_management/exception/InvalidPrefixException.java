package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidPrefixException extends AuthException {
    public InvalidPrefixException(String message) {
        super(message);
    }
}
