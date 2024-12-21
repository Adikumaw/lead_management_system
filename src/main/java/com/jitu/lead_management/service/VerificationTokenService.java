package com.jitu.lead_management.service;

import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.entity.VerificationToken;

public interface VerificationTokenService {
    VerificationToken save(VerificationToken token);

    void delete(VerificationToken token);

    void sender(String reference);

    void sender(User user, VerificationToken verificationToken);

    VerificationToken generate(int userId, String reference);

    VerificationToken findByUserId(int userId);
}
