package com.jitu.lead_management.service;

import com.jitu.lead_management.entity.User;

public interface UserService {
    // ----------------------------------------------------------------
    // service methods for user
    // ----------------------------------------------------------------
    int findUserIdByEmail(String email);

    User get(int userId);

    User get(String email);

    User save(User user);

    // UserViewModel getInfo(String reference);

    String getUserName(int userId);

    boolean isVerified(int userId);

    // Boolean existsByUserIdAndVerified(int userId);

    // Boolean existsByEmailAndNotVerified(String email);

    Boolean existsByEmailAndRefreshToken(String email, String refreshToken);
}
