package com.jitu.lead_management.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.Miscellaneous.EmailTemplate;
import com.jitu.lead_management.entity.UpdateVerificationToken;
import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.exception.InvalidPrefixException;
import com.jitu.lead_management.repository.UpdateVerificationTokenRepository;

@Service
public class UpdateVerificationTokenServiceImpl implements UpdateVerificationTokenService {

    private String updatePasswordLink = "http://localhost:8080/auth/verify-password-update?token=";
    private String applicationName = "Lead Management";
    private String emailSubjectUpdatePassword = "Confirm Your Password Change Request";
    private int expiration = 1;

    @Autowired
    private UpdateVerificationTokenRepository updateVerificationTokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JWTService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(UpdateVerificationTokenService.class);

    @Override
    public void generateAndSendPasswordUpdateVerification(User user, String encryptedPassword) {
        // Generate the token
        String token = jwtService.generateUpdatePasswordToken(user.getEmail());

        // Save token and encrypted password to the database
        save(user.getUserId(), encryptedPassword, PASSWORD, token);

        // Send verification email
        sendPasswordUpdateVerificationLink(user, token);
    }

    @Override
    public void sendPasswordUpdateVerificationLink(User user, String token) {
        String email = user.getEmail();
        String verificationTemplate = EmailTemplate.PASSWORD_UPDATE_VERIFICATION_TEMPLATE;
        String formatedMessage = String.format(verificationTemplate, user.getName(),
                updatePasswordLink + token, expiration,
                applicationName);

        try {
            emailService.sendEmail(email, emailSubjectUpdatePassword, formatedMessage);
        } catch (Exception e) {
            logger.error("Error sending email: " + e.getMessage(), e);
        }
    }

    @Override
    public void save(int userId, String data, int prefix, String token) {
        data = setPrefix(data, prefix);
        // check if another password update is not in progress
        UpdateVerificationToken updateVerificationToken = findByUserIdAndPrefix(userId, prefix);
        if (updateVerificationToken == null) {
            updateVerificationToken = new UpdateVerificationToken(userId, data, token);
        } else {
            updateVerificationToken.setData(data);
            updateVerificationToken.setToken(token);
        }

        updateVerificationTokenRepository.save(updateVerificationToken);
    }

    @Override
    public UpdateVerificationToken findByData(String data) {
        return updateVerificationTokenRepository.findByData(data).orElse(null);
    }

    @Override
    public UpdateVerificationToken findByUserIdAndPrefix(int userId, int prefix) {
        if (prefix == PASSWORD) {
            return updateVerificationTokenRepository.findByUserIdAndDataStartingWith(userId, "password:").orElse(null);
        } else if (prefix == EMAIL) {
            return updateVerificationTokenRepository.findByUserIdAndDataStartingWith(userId, "email:").orElse(null);
        }
        throw new InvalidPrefixException("Invalid prefix: " + prefix);
    }

    @Override
    public String getPrefix(String data) {
        if (data == null || data.isEmpty()) {
            return ""; // Return empty string for null or empty input
        }
        int indexOfColon = data.indexOf(":");
        return indexOfColon > 0 ? data.substring(0, indexOfColon) : data;
    }

    @Override
    public String setPrefix(String data, int prefix) {
        if (prefix == PASSWORD) {
            return "password:" + data;
        } else if (prefix == EMAIL) {
            return "email:" + data;
        }
        throw new InvalidPrefixException("Invalid prefix: " + prefix);
    }

}
