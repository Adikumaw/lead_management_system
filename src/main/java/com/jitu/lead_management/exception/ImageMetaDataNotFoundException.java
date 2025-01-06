package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ImageMetaDataNotFoundException extends ImageExceptions {
    public ImageMetaDataNotFoundException(String message) {
        super(message);
    }
}
