package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FailedToParseDateTimeException extends LeadException {
    public FailedToParseDateTimeException(String message) {
        super(message);
    }
}
