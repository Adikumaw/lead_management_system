package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidJWTHeaderException extends JwtException {
    public InvalidJWTHeaderException(String message) {
        super(message);
    }
}
