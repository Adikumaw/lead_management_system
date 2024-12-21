package com.jitu.lead_management.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.Miscellaneous.Miscellaneous;
import com.jitu.lead_management.entity.UpdateVerificationToken;
import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.entity.VerificationToken;
import com.jitu.lead_management.exception.InvalidPasswordException;
import com.jitu.lead_management.exception.InvalidUpdateVerificationTokenException;
import com.jitu.lead_management.exception.LeadManagementException;
import com.jitu.lead_management.exception.TooManyLoginAttemptsException;
import com.jitu.lead_management.exception.UnableToLoginException;
import com.jitu.lead_management.exception.UnableToRefreshTokenException;
import com.jitu.lead_management.exception.UpdateVerificationFailedException;
import com.jitu.lead_management.exception.UpdateVerificationTokenExpiredException;
import com.jitu.lead_management.exception.UpdateVerificationTokenNotFoundException;
import com.jitu.lead_management.exception.UserNotFoundException;
import com.jitu.lead_management.model.JwtResponse;
import com.jitu.lead_management.model.PasswordUpdateModel;
import com.jitu.lead_management.model.ResetRequestModel;
import com.jitu.lead_management.model.SignInModel;
import com.jitu.lead_management.model.SignInResponse;
import com.jitu.lead_management.model.SignUpModel;

@Service
public class AuthServiceImpl implements AuthService {

    private final int MAX_LOGIN_ATTEMPTS = 5;
    private static final long LOCK_EXPIRATION_TIME = 24L * 60 * 60 * 1000; // 24 hours in milliseconds
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private ResetRequestService resetRequestService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationService verificationService;
    @Autowired
    private UpdateVerificationTokenService updateVerificationTokenService;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Override
    public void register(SignUpModel signUpModel) {
        User user = null;
        try {
            user = userService.get(signUpModel.getEmail());
        } catch (UserNotFoundException e) {
            // Do nothing...
        }

        // vrify user details
        verificationService.verifyUserDetails(signUpModel, user);

        // encrypt password
        String encryptedPassword;
        encryptedPassword = passwordEncoder.encode(signUpModel.getPassword());
        signUpModel.setPassword(encryptedPassword);

        // fetch the old user id if it exists
        if (user != null) {
            user = new User(user.getUserId(), signUpModel);
        } else {
            user = new User(signUpModel);
        }

        // save new user
        user = userService.save(user);

        // Generate Verification Token
        VerificationToken verificationToken = verificationTokenService.generate(user.getUserId());
        // send verification email
        verificationTokenService.sender(user, verificationToken);
    }

    @Override
    public boolean verify(String token) {
        // fetch token from Database
        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        // check if token exist and not expired
        if (verificationTokenService.verify(verificationToken)) {
            // fetch and set user active
            int userId = verificationToken.getUserId();
            User user = userService.get(userId);
            user.setActive(1);
            user.setVerified(1);
            userService.save(user);
            // Delete verification token
            verificationTokenService.delete(verificationToken);
            return true;
        }
        return false;
    }

    @Override
    public SignInResponse authenticateAndGenerateTokens(SignInModel signInRequest) {
        // Authenticate user
        try {
            doAuthenticate(signInRequest.getReference(), signInRequest.getPassword());
        } catch (InternalAuthenticationServiceException | BadCredentialsException e) {
            // Allow not Verified Exception to pass through
            if (e.getMessage().equals("Please verify your email to login")) {
                throw new com.jitu.lead_management.exception.BadCredentialsException(e.getMessage());
            }
            // Allow Too many login attempts exception to pass through
            else if (e.getMessage().startsWith("too many login attempts")) {
                throw new com.jitu.lead_management.exception.BadCredentialsException(e.getMessage());
            }
            // Check for manage login attempt and throw custom bad credentials exception
            else {
                // Check if password is wrong then manage login attempt
                if (e.getClass() == BadCredentialsException.class) {
                    manageLoginAttempts(signInRequest.getReference());
                }
                // finaly throw the exception
                throw new com.jitu.lead_management.exception.BadCredentialsException(
                        "Invalid E-mail or Password");
            }
        }
        // Generate JWT token
        String token = jwtService.generateToken(signInRequest.getReference());
        String refreshToken = jwtService.generateRefreshToken(signInRequest.getReference());

        manageLogin(signInRequest.getReference(), refreshToken);

        // Build and return response
        return SignInResponse.builder()
                .refreshToken(refreshToken)
                .jwtToken(token)
                .reference(signInRequest.getReference())
                .build();
    }

    @Override
    public JwtResponse authenticateAndRefreshToken(String refreshToken) {
        String reference = jwtService.fetchReference(refreshToken);
        // Validate and fetch user details
        // Boolean isExpired = jwtService.isTokenExpired(refreshToken);

        if (!userService.existsByEmailAndRefreshToken(reference, refreshToken)) {
            throw new UnableToRefreshTokenException("Error: Invalid Refresh Token");
        }
        // if (isExpired) {
        // throw new UnableToRefreshTokenException("Error: Token expired try Sign-In");
        // }

        // Generate new JWT token
        String token = jwtService.generateToken(reference);

        // Build and return response
        return JwtResponse.builder()
                .jwtToken(token)
                .build();
    }

    @Override
    public void logout(String reference) {
        User user = userService.get(reference);
        user.setRefreshToken(null);
        user.setLogin(0);
        user = userService.save(user);
        if (user == null) {
            throw new UnableToLoginException("Unable to sign-in! Please try again.");
        }
    }

    @Override
    public void requestReset(ResetRequestModel resetRequest) {
        try {
            // fetch the user information
            User user = userService.get(resetRequest.getEmail());
            // check if user is verified
            verificationService.checkUserVerified(user);

            // Generate reset request token
            String token = jwtService.generateResetRequestToken(user.getEmail());

            // save token to db
            resetRequestService.save(user.getUserId(), token);

            // Send reset request email
            resetRequestService.sendResetRequestLink(user, token);
        } catch (LeadManagementException e) {
            // Do nothing...
        }
    }

    @Override
    public void updatePassword(String reference, PasswordUpdateModel passwordUpdateModel) {
        User user = userService.get(reference);
        // check if password is correct
        if (!passwordEncoder.matches(passwordUpdateModel.getOldPassword(), user.getPassword())) {
            throw new com.jitu.lead_management.exception.BadCredentialsException("Old Password is incorrect");
        }
        // check if the new password is strong enough
        if (!Miscellaneous.isStrongPassword(passwordUpdateModel.getNewPassword())) {
            throw new InvalidPasswordException("Error: Weak password Error");
        }
        // encrypt new password
        String encryptedPassword = passwordEncoder.encode(passwordUpdateModel.getNewPassword());
        // save user to database and send verification email
        updateVerificationTokenService.generateAndSendPasswordUpdateVerification(user, encryptedPassword);
    }

    @Override
    public void verifyPasswordUpdate(String token) {
        try {
            // Step 1: Check token expiration
            if (jwtService.isTokenExpired(token)) {
                throw new UpdateVerificationTokenExpiredException("this link is expired");
            }
            // Step 2: Extract reference and fetch user
            String reference = jwtService.fetchReference(token);
            User user = userService.get(reference);
            // Step 3: Retrieve update verification token
            UpdateVerificationToken updateVerificationToken = updateVerificationTokenService
                    .findByUserIdAndPrefix(user.getUserId(), UpdateVerificationTokenService.PASSWORD);
            if (updateVerificationToken == null) {
                throw new UpdateVerificationTokenNotFoundException("UpdateVerificationToken not found");
            }
            // Step 4: Match tokens
            if (!token.equals(updateVerificationToken.getToken())) {
                throw new InvalidUpdateVerificationTokenException("token mismatch");
            }
            // Step 5: Update password securely
            String newPassword = updateVerificationTokenService.resolveData(updateVerificationToken.getData());
            user.setPassword(newPassword);
            userService.save(user);

            // Step 6: Clean up the token
            updateVerificationTokenService.delete(updateVerificationToken);

        } catch (UpdateVerificationTokenExpiredException e) {
            throw e;
        } catch (Exception e) {
            // Log and handle unexpected exceptions
            logger.error("Unexpected error during password update verification", e);
            throw new UpdateVerificationFailedException("Verification failed, try again");
        }
    }

    // =================================================================
    // HELPER FUNCTIONS
    // =================================================================
    public void doAuthenticate(String reference, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(reference,
                password);

        authenticationManager.authenticate(authentication);
    }

    private void manageLogin(String reference, String refreshToken) {
        User user = userService.get(reference);
        user.setRefreshToken(refreshToken);
        user.setLogin(1);
        // clear locks and attemps
        user.setLoginAttempts(0);
        user.setLockExpirationTime(null);
        // save user to database
        user = userService.save(user);
        if (user == null) {
            throw new UnableToLoginException("Unable to sign-in! Please try again.");
        }
    }

    private void manageLoginAttempts(String reference) {
        User user = userService.get(reference);
        // check if lockExpirationTime is available or not.
        if (user.getLockExpirationTime() != null) {
            // when lock is expired
            if (user.getLockExpirationTime().before(new Date())) {
                user.setLoginAttempts(0);
                user.setLockExpirationTime(null);
            }
            // when lock is not expired and max login attempts is reached
            else if (user.getLoginAttempts() >= MAX_LOGIN_ATTEMPTS) {
                long lockExpirationTimeLeft = user.getLockExpirationTime().getTime() - new Date().getTime();
                long totalSeconds = lockExpirationTimeLeft / 1000; // Convert milliseconds to seconds
                long hours = totalSeconds / 3600; // Calculate hours
                long minutes = (totalSeconds % 3600) / 60;

                throw new TooManyLoginAttemptsException("too many login attempts, try after "
                        + hours + " hours and " + minutes + " minutes");
            }
        }

        // increment login attempts and lock account for 24 hours
        user.incrementLoginAttempts();
        user.setLockExpirationTime(new Date(new Date().getTime() + LOCK_EXPIRATION_TIME));
        // save user to database
        user = userService.save(user);
    }

}
