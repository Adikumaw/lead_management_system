package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductHotelNotFoundException extends ProductException {
    public ProductHotelNotFoundException(String message) {
        super(message);
    }

}
