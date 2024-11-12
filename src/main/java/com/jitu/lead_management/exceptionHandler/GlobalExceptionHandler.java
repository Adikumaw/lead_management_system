package com.jitu.lead_management.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jitu.lead_management.exception.LeadManagementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ----------------------------------------------------------------
    // UNIVERSAL RUNTIME EXCEPTIONS
    // ----------------------------------------------------------------
    @ExceptionHandler
    public ResponseEntity<String> handleException(LeadManagementException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
