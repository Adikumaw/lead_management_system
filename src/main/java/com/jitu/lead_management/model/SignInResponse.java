package com.jitu.lead_management.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponse {
    private String reference;
    private String jwtToken;
    private String refreshToken;
}
