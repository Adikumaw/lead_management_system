package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadCredentialsException extends AuthException {
    public BadCredentialsException(String message) {
        super(message);
    }
}
