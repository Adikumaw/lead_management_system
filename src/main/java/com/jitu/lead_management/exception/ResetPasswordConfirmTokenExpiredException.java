package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResetPasswordConfirmTokenExpiredException extends AuthException {
    public ResetPasswordConfirmTokenExpiredException(String message) {
        super(message);
    }
}
