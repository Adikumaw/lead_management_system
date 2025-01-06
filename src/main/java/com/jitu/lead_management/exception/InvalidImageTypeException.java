package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidImageTypeException extends ImageExceptions {
    public InvalidImageTypeException(String message) {
        super(message);
    }
}
