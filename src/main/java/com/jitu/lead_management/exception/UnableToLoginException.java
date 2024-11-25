package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnableToLoginException extends AuthException {
    public UnableToLoginException(String message) {
        super(message);
    }
}
