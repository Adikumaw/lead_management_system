package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ImageNotFoundException extends ImageExceptions {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
