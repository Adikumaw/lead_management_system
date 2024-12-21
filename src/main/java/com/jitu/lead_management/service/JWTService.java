package com.jitu.lead_management.service;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateToken(String reference);

    String generateRefreshToken(String reference);

    String generateResetPasswordRequestToken(String reference);

    String generateUpdatePasswordToken(String reference);

    String fetchReference(String token);

    Date fetchExpirationDate(String token);

    Boolean isTokenExpired(String token);

    Boolean validateToken(String token, UserDetails user);

    String resolveJwtHeader(String header);

    String resolveReference(String header);
}
