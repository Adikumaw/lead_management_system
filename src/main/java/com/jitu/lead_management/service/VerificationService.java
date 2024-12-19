package com.jitu.lead_management.service;

import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.model.SignUpModel;

public interface VerificationService {
    void checkUserVerified(User user);

    void checkUserLoginAllowed(User user);

    User setUserActive(User user);

    void verifyUserDetails(SignUpModel signUpModel, User user);

}
