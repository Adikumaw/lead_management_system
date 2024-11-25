package com.jitu.lead_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.exception.UnableToLoginException;
import com.jitu.lead_management.exception.UnableToRefreshTokenException;
import com.jitu.lead_management.model.JwtResponse;
import com.jitu.lead_management.model.SignInModel;
import com.jitu.lead_management.model.SignInResponse;

@Service
public class AuthServiceImpl implements AuthService {

    private final int MAX_LOGIN_ATTEMPTS = 5;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Override
    public SignInResponse authenticateAndGenerateTokens(SignInModel signInRequest) {
        // Authenticate user
        try {
            doAuthenticate(signInRequest.getReference(), signInRequest.getPassword());
        } catch (InternalAuthenticationServiceException | BadCredentialsException e) {
            // Do increment login attempts only if password is wrong.
            if (e.getMessage().equals("Please verify your email to login")) {
                throw new com.jitu.lead_management.exception.BadCredentialsException(e.getMessage());
            } else {
                throw new com.jitu.lead_management.exception.BadCredentialsException(
                        "Invalid E-mail or Password");
            }
        }
        // Generate JWT token
        String token = jwtService.generateToken(signInRequest.getReference());
        String refreshToken = jwtService.generateRefreshToken(signInRequest.getReference());

        manageLogin(signInRequest.getReference(), refreshToken);

        // Build and return response
        return SignInResponse.builder()
                .refreshToken(refreshToken)
                .jwtToken(token)
                .reference(signInRequest.getReference())
                .build();
    }

    @Override
    public JwtResponse authenticateAndRefreshToken(String refreshToken) {
        String reference = jwtService.fetchReference(refreshToken);
        // Validate and fetch user details
        Boolean isExpired = jwtService.isTokenExpired(refreshToken);

        if (!userService.existsByEmailAndRefreshToken(reference, refreshToken)) {
            throw new UnableToRefreshTokenException("Error: Invalid Refresh Token");
        }
        // if (isExpired) {
        // throw new UnableToRefreshTokenException("Error: Token expired try Sign-In");
        // }

        // Generate new JWT token
        String token = jwtService.generateToken(reference);

        // Build and return response
        return JwtResponse.builder()
                .jwtToken(token)
                .build();
    }

    @Override
    public void doAuthenticate(String reference, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(reference,
                password);

        authenticationManager.authenticate(authentication);
    }

    // @Override
    // public void logout(String reference) {
    // if (!userService.userLogout(reference)) {
    // throw new UnableToLogoutException("User not logged out!!!");
    // }
    // }

    private void manageLogin(String reference, String refreshToken) {
        User user = userService.get(reference);
        user.setRefreshToken(refreshToken);
        user.setLogin(1);
        user = userService.save(user);
        if (user == null) {
            throw new UnableToLoginException("Unable to sign-in! Please try again.");
        }
    }

}
