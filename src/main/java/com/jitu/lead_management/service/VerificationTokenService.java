package com.jitu.lead_management.service;

import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.entity.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);

    VerificationToken save(VerificationToken token);

    void delete(VerificationToken token);

    void sender(String reference);

    void sender(User user, VerificationToken verificationToken);

    boolean verify(String token);

    boolean verify(VerificationToken token);

    VerificationToken generate(int userId);
}
