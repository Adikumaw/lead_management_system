package com.jitu.lead_management.service;

import com.jitu.lead_management.entity.User;

public interface ResetRequestService {
    void sendResetPasswordConfirmLink(User user, String token);

    void save(int userId, String token);
}
