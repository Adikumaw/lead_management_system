package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TooManyLoginAttemptsException extends AuthException {
    public TooManyLoginAttemptsException(String message) {
        super(message);
    }
}
