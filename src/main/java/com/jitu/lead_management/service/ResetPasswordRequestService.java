package com.jitu.lead_management.service;

import com.jitu.lead_management.entity.ResetPasswordRequest;
import com.jitu.lead_management.entity.User;

public interface ResetPasswordRequestService {
    void sendResetPasswordConfirmLink(User user, String token);

    void save(int userId, String token);

    ResetPasswordRequest findByUserId(int userId);

    void delete(ResetPasswordRequest resetPasswordRequest);
}
