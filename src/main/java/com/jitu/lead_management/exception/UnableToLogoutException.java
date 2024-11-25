package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnableToLogoutException extends AuthException {
    public UnableToLogoutException(String message) {
        super(message);
    }
}
