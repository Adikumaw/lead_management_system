package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResetPasswordConfirmFailed extends AuthException {
    public ResetPasswordConfirmFailed(String message) {
        super(message);
    }
}
