package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidTokenException extends UserException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
