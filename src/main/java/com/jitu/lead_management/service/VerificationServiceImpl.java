package com.jitu.lead_management.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.Miscellaneous.Miscellaneous;
import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.exception.InvalidEmailException;
import com.jitu.lead_management.exception.InvalidPasswordException;
import com.jitu.lead_management.exception.TooManyLoginAttemptsException;
import com.jitu.lead_management.exception.UserException;
import com.jitu.lead_management.exception.UserExistException;
import com.jitu.lead_management.exception.UserNotVerifiedException;
import com.jitu.lead_management.model.SignUpModel;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final int MAX_LOGIN_ATTEMPTS = 5;
    @Autowired
    private UserService userService;

    @Override
    public void checkUserVerified(User user) {
        if (!user.isVerified()) {
            throw new UserNotVerifiedException("Please verify your email to login");
        }
    }

    @Override
    public User setUserActive(User user) {
        // Verify that the user is Active
        if (!user.isActive()) {
            user.setActive(1);
            user = userService.save(user);
        }
        return user;
    }

    @Override
    public void checkUserLoginAllowed(User user) {
        // check if lock is not null AND lock is not expired AND max login attempts have
        // been reached
        if (user.getLockExpirationTime() != null && !user.getLockExpirationTime().before(new Date())
                && user.getLoginAttempts() >= MAX_LOGIN_ATTEMPTS) {
            long lockExpirationTimeLeft = user.getLockExpirationTime().getTime() - new Date().getTime();
            long totalSeconds = lockExpirationTimeLeft / 1000; // Convert milliseconds to seconds
            long hours = totalSeconds / 3600; // Calculate hours
            long minutes = (totalSeconds % 3600) / 60;

            throw new TooManyLoginAttemptsException("too many login attempts, try after "
                    + hours + " hours and " + minutes + " minutes");
        }
    }

    @Override
    public void verifyUserDetails(SignUpModel signUpModel, User user) {
        String name = signUpModel.getUserName();
        String email = signUpModel.getEmail();
        String password = signUpModel.getPassword();

        // checking user name
        if (name == null || name == "") {
            throw new UserException("Error: Invalid user name");
        }

        // checking user email
        if (email == null || email == "") {
            throw new InvalidEmailException("Error: Empty email address Error");
        } else if (!Miscellaneous.isValidEmail(email)) {
            throw new InvalidEmailException("Error: Invalid email Address");
        } else if (existsByEmailAndVerified(user)) {
            throw new UserExistException("Email already exists, try login !!!");
        }

        // checking user password
        if (password == null || password.isEmpty()) {
            throw new InvalidPasswordException("Error: Invalid password Error");
        } else if (!Miscellaneous.isStrongPassword(password)) {
            throw new InvalidPasswordException("Error: Weak password Error");
        }
    }

    private Boolean existsByEmailAndVerified(User user) {
        return user != null && user.getVerified() == 1;
    }
}
