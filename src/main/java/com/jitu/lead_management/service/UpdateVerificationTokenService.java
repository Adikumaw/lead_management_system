package com.jitu.lead_management.service;

import com.jitu.lead_management.entity.UpdateVerificationToken;
import com.jitu.lead_management.entity.User;

public interface UpdateVerificationTokenService {

    public static final Integer PASSWORD = 1;
    public static final Integer EMAIL = 2;

    // UpdateVerificationToken findByToken(String token);

    // UpdateVerificationToken findByData(String data);

    // UpdateVerificationToken save(UpdateVerificationToken token);

    // void delete(UpdateVerificationToken token);

    // void sender(String reference, String verificationLink);

    // void sender(User user, UpdateVerificationToken updateVerificationToken,
    // String verificationLink);

    // boolean verify(String token);

    // boolean verify(UpdateVerificationToken token);

    void generateAndSendPasswordUpdateVerification(User user, String encryptedPassword);

    void sendPasswordUpdateVerificationLink(User user, String token);

    void save(int userId, String data, int prefix, String token);

    UpdateVerificationToken findByData(String data);

    UpdateVerificationToken findByUserIdAndPrefix(int userId, int prefix);

    String setPrefix(String data, int prefix);

    String getPrefix(String data);

    // String getPrefix(String data);

    // String setPrefix(String data, String prefix);

    // String fetchData(String data);
}
