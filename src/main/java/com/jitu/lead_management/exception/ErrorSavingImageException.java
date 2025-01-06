package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorSavingImageException extends ImageExceptions {
    public ErrorSavingImageException(String message) {
        super(message);
    }
}
