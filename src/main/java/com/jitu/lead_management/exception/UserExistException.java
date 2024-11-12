package com.jitu.lead_management.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserExistException extends UserException {
    public UserExistException(String message) {
        super(message);
    }
}
