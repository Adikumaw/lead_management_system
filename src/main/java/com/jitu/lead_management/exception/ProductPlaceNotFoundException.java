package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductPlaceNotFoundException extends ProductException {
    public ProductPlaceNotFoundException(String message) {
        super(message);
    }

}
