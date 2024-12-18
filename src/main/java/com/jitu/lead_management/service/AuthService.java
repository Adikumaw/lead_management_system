package com.jitu.lead_management.service;

import com.jitu.lead_management.model.JwtResponse;
import com.jitu.lead_management.model.ResetRequestModel;
import com.jitu.lead_management.model.SignInModel;
import com.jitu.lead_management.model.SignInResponse;
import com.jitu.lead_management.model.SignUpModel;

public interface AuthService {
    public void register(SignUpModel signUpModel);

    boolean verify(String token);

    SignInResponse authenticateAndGenerateTokens(SignInModel signInRequest);

    JwtResponse authenticateAndRefreshToken(String refreshToken);

    void logout(String reference);

    void requestReset(ResetRequestModel resetRequest);

}
