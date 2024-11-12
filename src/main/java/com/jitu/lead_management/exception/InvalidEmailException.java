package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidEmailException extends UserException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
