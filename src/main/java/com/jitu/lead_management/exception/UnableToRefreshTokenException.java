package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnableToRefreshTokenException extends AuthException {
    public UnableToRefreshTokenException(String message) {
        super(message);
    }
}
