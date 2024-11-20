package com.jitu.lead_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.model.JwtResponse;
import com.jitu.lead_management.model.SignInModel;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JwtResponse authenticateAndGenerateToken(SignInModel signInRequest) {
        // Authenticate user
        doAuthenticate(signInRequest.getReference(), signInRequest.getPassword());

        // Load user details
        // Removing loadUserbyUsername cause user is already authenticated...
        // UserDetails userDetails =
        // customUserDetailsService.loadUserByUsername(signInRequest.getReference());

        // Generate JWT token
        String token = jwtService.generateToken(signInRequest.getReference());

        // Build and return response
        return JwtResponse.builder()
                .jwtToken(token)
                .reference(signInRequest.getReference())
                .build();
    }

    @Override
    public void doAuthenticate(String reference, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(reference,
                password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new com.jitu.lead_management.exception.BadCredentialsException(
                    "Error: Invalid Username or Password !!");
        }
    }
}
