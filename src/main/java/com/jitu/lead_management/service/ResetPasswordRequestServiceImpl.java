package com.jitu.lead_management.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.Miscellaneous.EmailTemplate;
import com.jitu.lead_management.entity.ResetPasswordRequest;
import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.repository.ResetPasswordRequestRepository;

@Service
public class ResetPasswordRequestServiceImpl implements ResetPasswordRequestService {
    @Value("${password.reset.request.confirm.url}")
    private String resetRequestLink;
    @Value("${spring.application.name}")
    private String applicationName;
    private String emailSubject = "Reset Your Password";
    private int expiration = 1;

    @Autowired
    private ResetPasswordRequestRepository resetPasswordRequestRepository;
    @Autowired
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordRequestService.class);

    @Override
    public void sendResetPasswordConfirmLink(User user, String token) {
        String email = user.getEmail();
        String verificationTemplate = EmailTemplate.PASSWORD_RESET_REQUEST_TEMPLATE;
        String formatedMessage = String.format(verificationTemplate, user.getName(),
                resetRequestLink + token, expiration,
                applicationName);

        try {
            emailService.sendEmail(email, emailSubject, formatedMessage);
        } catch (Exception e) {
            logger.error("Error sending email: " + e.getMessage(), e);
        }
    }

    @Override
    public ResetPasswordRequest findByUserId(int userId) {
        return resetPasswordRequestRepository.findById(userId).orElse(null);
    }

    @Override
    public void save(int userId, String token) {
        ResetPasswordRequest resetRequest = new ResetPasswordRequest(userId, token);
        resetPasswordRequestRepository.save(resetRequest);
    }

    @Override
    public void delete(ResetPasswordRequest resetPasswordRequest) {
        resetPasswordRequestRepository.delete(resetPasswordRequest);
    }

}
