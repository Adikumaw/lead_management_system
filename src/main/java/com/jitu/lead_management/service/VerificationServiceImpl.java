package com.jitu.lead_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.Miscellaneous.Miscellaneous;
import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.exception.InvalidEmailException;
import com.jitu.lead_management.exception.InvalidPasswordException;
import com.jitu.lead_management.exception.UserException;
import com.jitu.lead_management.exception.UserExistException;
import com.jitu.lead_management.model.SignUpModel;

@Service
public class VerificationServiceImpl implements VerificationService {
    @Autowired
    private UserService userService;

    @Override
    public Boolean isUserVerified(User user) {
        if (!user.isVerified()) {
            throw new BadCredentialsException("User not verified with Reference : " + user.getEmail());
        }
        return true;
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
            throw new UserExistException("Warning: Email already exists, try login !!!");
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
