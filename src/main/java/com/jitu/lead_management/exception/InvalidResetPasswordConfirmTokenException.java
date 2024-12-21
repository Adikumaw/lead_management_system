package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidResetPasswordConfirmTokenException extends AuthException {
    public InvalidResetPasswordConfirmTokenException(String message) {
        super(message);
    }
}
