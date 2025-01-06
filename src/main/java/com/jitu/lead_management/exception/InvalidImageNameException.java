package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidImageNameException extends ImageExceptions {
    public InvalidImageNameException(String message) {
        super(message);
    }
}
