package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductRestaurantNotFoundException extends ProductException {
    public ProductRestaurantNotFoundException(String message) {
        super(message);
    }

}
