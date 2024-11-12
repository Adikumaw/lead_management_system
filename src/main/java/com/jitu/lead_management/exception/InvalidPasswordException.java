package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidPasswordException extends UserException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
