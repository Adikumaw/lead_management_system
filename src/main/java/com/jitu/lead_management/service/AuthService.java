package com.jitu.lead_management.service;

import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.model.JwtResponse;
import com.jitu.lead_management.model.SignInModel;

public interface AuthService {

    JwtResponse authenticateAndGenerateToken(SignInModel signInRequest);

    void doAuthenticate(String reference, String password);

    Boolean isUserVerified(User user);

    User setUserActive(User user);
}
