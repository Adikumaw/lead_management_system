package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotVerifiedException extends AuthException {
    public UserNotVerifiedException(String message) {
        super(message);
    }
}
