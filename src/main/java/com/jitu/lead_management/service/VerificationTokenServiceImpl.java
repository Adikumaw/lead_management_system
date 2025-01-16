package com.jitu.lead_management.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.Miscellaneous.EmailTemplate;
import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.entity.VerificationToken;
import com.jitu.lead_management.exception.VerificationTokenNotFoundException;
import com.jitu.lead_management.repository.VerificationTokenRepository;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Value("${account.verify.url}")
    private String accountVerifyLink;
    @Value("${spring.application.name}")
    private String applicationName;
    private String emailSubject = "Verify Your Email Address for " + applicationName;
    private int EXPIRATION = 24;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(VerificationTokenServiceImpl.class);

    @Override
    public void sender(String reference) {
        User user = userService.get(reference);
        Optional<VerificationToken> OptionalVerificationToken = verificationTokenRepository.findById(user.getUserId());
        VerificationToken verificationToken = null;

        if (OptionalVerificationToken.isPresent()) {
            verificationToken = OptionalVerificationToken.get();
        } else {
            logger.error("No verification token found for user: " + reference);
            throw new VerificationTokenNotFoundException("Warning: invalid Atempt to verify");
        }

        String email = user.getEmail();
        String token = verificationToken.getToken();
        String verificationTemplate = EmailTemplate.EMAIL_VERIFICATION_TEMPLATE;
        String formatedMessage = String.format(verificationTemplate, user.getName(), applicationName,
                accountVerifyLink + token, EXPIRATION, applicationName,
                applicationName);

        try {
            emailService.sendEmail(email, emailSubject, formatedMessage);
        } catch (Exception e) {
            logger.error("Error sending email: " + e.getMessage(), e);
        }
    }

    @Override
    public void sender(User user, VerificationToken verificationToken) {
        String email = user.getEmail();
        String token = verificationToken.getToken();
        String verificationTemplate = EmailTemplate.EMAIL_VERIFICATION_TEMPLATE;
        String formatedMessage = String.format(verificationTemplate, user.getName(), applicationName,
                accountVerifyLink + token, EXPIRATION, applicationName,
                applicationName);

        try {
            emailService.sendEmail(email, emailSubject, formatedMessage);
        } catch (Exception e) {
            logger.error("Error sending email: " + e.getMessage(), e);
        }
    }

    @Override
    public VerificationToken findByUserId(int userId) {
        return verificationTokenRepository.findById(userId).orElse(null);
    }

    @Override
    public VerificationToken save(VerificationToken token) {
        return verificationTokenRepository.save(token);
    }

    @Override
    public void delete(VerificationToken token) {
        verificationTokenRepository.delete(token);
    }

    @Override
    public VerificationToken generate(int userId, String reference) {
        // Generate Verification Token
        String token = jwtService.generateVerificationToken(reference);

        VerificationToken verificationToken = new VerificationToken(userId, token);

        return save(verificationToken);
    }
}
