package com.jitu.lead_management.service;

import com.jitu.lead_management.model.SignUpModel;

public interface UserAdvanceService {
    // ----------------------------------------------------------------
    // RestApi methods for user
    // ----------------------------------------------------------------

    boolean register(SignUpModel signInModel);

    boolean verify(String token);

    // boolean verifyUpdate(String token);

    // UserViewModel updateName(int userId, String name);

    // UserViewModel updateName(String reference, String name);

    // void updateEmail(int userId, String email);

    // void updateEmail(String reference, String email);

    // void updatePassword(int userId, String oldPassword, String newPassword);

    // void updatePassword(String reference, String oldPassword, String
    // newPassword);

    // boolean deactivate(int userId, String password);

    // boolean deactivate(String reference, String password);

    // void delete(int userId);

    // void delete(String reference);
}
