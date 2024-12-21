package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResetPasswordRequestNotFoundException extends AuthException {
    public ResetPasswordRequestNotFoundException(String message) {
        super(message);
    }
}
