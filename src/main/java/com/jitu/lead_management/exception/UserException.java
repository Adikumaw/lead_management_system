package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends LeadManagementException {
    public UserException(String message) {
        super(message);
    }
}
