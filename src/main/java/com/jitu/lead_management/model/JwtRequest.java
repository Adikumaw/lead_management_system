package com.jitu.lead_management.model;

import lombok.Data;

@Data
public class JwtRequest {
    private String reference;
    private String password;
}
