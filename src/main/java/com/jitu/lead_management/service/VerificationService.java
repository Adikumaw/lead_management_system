package com.jitu.lead_management.service;

import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.model.SignUpModel;

public interface VerificationService {
    Boolean isUserVerified(User user);

    User setUserActive(User user);

    void verifyUserDetails(SignUpModel signUpModel, User user);

}
