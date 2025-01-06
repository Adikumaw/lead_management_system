package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorFetchingImageExtention extends ImageExceptions {
    public ErrorFetchingImageExtention(String message) {
        super(message);
    }
}
