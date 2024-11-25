package com.jitu.lead_management.service;

import com.jitu.lead_management.model.JwtResponse;
import com.jitu.lead_management.model.SignInModel;
import com.jitu.lead_management.model.SignInResponse;

public interface AuthService {

    SignInResponse authenticateAndGenerateTokens(SignInModel signInRequest);

    JwtResponse authenticateAndRefreshToken(String refreshToken);

    void doAuthenticate(String reference, String password);

    // void logout(String reference);

}
