package com.jitu.lead_management.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.Miscellaneous.Miscellaneous;
import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.entity.VerificationToken;
import com.jitu.lead_management.exception.InvalidEmailException;
import com.jitu.lead_management.exception.InvalidPasswordException;
import com.jitu.lead_management.exception.UserException;
import com.jitu.lead_management.exception.UserExistException;
import com.jitu.lead_management.model.SignInModel;

@Service
public class UserAdvanceServiceImpl implements UserAdvanceService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenService verificationTokenService;
    // @Autowired
    // private UpdateVerificationTokenService updateVerificationTokenService;

    // private static final String verificationLink =
    // "http://localhost:8080/api/users/verify-update?token=";

    private static final Logger logger = LoggerFactory.getLogger(UserAdvanceServiceImpl.class);

    // ----------------------------------------------------------------
    // service methods for user
    // ----------------------------------------------------------------

    @Override
    public boolean register(SignInModel signInModel) {
        // vrify user details
        verifyUserDetails(signInModel);

        // encrypt password
        String encryptedPassword;
        encryptedPassword = passwordEncoder.encode(signInModel.getPassword());
        signInModel.setPassword(encryptedPassword);

        User user = new User(signInModel);

        // fetch the old user id if it exists
        if (userService.existsByEmailAndNotVerified(signInModel.getEmail())) {
            int userId = userService.findUserIdByEmail(signInModel.getEmail());
            user.setUserId(userId);
        }

        // create new user from user model
        user.setActive(0);
        user = userService.save(user);

        // Generate Verification Token
        VerificationToken verificationToken = verificationTokenService.generate(user.getUserId());
        // send verification email
        verificationTokenService.sender(user, verificationToken);

        return (user != null) ? true : false;
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

    // @Override
    // public boolean verifyUpdate(String token) {
    // // fetch token from Database
    // UpdateVerificationToken updateVerificationToken =
    // updateVerificationTokenService.findByToken(token);
    // // check if token exist and not expired
    // if (updateVerificationTokenService.verify(updateVerificationToken)) {
    // // fetch and set updated value to user
    // int userId = updateVerificationToken.getUserId();
    // String dataWithPrefix = updateVerificationToken.getData();
    // String prefix = updateVerificationTokenService.getPrefix(dataWithPrefix);
    // String data = updateVerificationTokenService.fetchData(dataWithPrefix);
    // User user = userRepository.findById(userId).get();
    // // check if data is number or email
    // if (prefix.equals("email")) {
    // user.setEmail(data);
    // }
    // if (prefix.equals("number")) {
    // user.setNumber(data);
    // }
    // // Save updated value to user
    // userRepository.save(user);

    // // Delete verification token
    // updateVerificationTokenService.delete(updateVerificationToken);
    // return true;
    // }
    // try {
    // updateVerificationTokenService.delete(updateVerificationToken);
    // } catch (InvalidDataAccessApiUsageException e) {
    // logger.error("Error deleting verification token: " + e.getMessage(), e);
    // } catch (Exception e) {
    // logger.error("Unknown error: " + e.getMessage(), e);
    // }
    // return false;
    // }

    // @Override
    // public UserViewModel updateName(int userId, String name) {
    // User fetchedUser = userService.get(userId);
    // if (fetchedUser != null) {
    // fetchedUser.setName(name);
    // return new UserViewModel(userRepository.save(fetchedUser));
    // }
    // return null;
    // }

    // @Override
    // public UserViewModel updateName(String reference, String name) {
    // int userId = userService.findUserIdByReference(reference);

    // User fetchedUser = userService.get(userId);
    // if (fetchedUser != null) {
    // fetchedUser.setName(name);
    // return new UserViewModel(userRepository.save(fetchedUser));
    // }
    // return null;
    // }

    // @Override
    // public void updateEmail(int userId, String email) {
    // // check if email is already userd or not
    // try {
    // userService.get(email); // it will throw exception if user not found ...
    // throw new UserExistException("Error: " + email + " is already used");
    // } catch (UserNotFoundException exc) {
    // User user = userService.get(userId);
    // boolean verifyEmail = Miscellaneous.isValidEmail(email);
    // if (user != null && verifyEmail) {
    // // Generate Verification Token
    // UpdateVerificationToken updateVerificationToken =
    // updateVerificationTokenService.generate(
    // userId,
    // email,
    // "email");

    // // send verification email
    // updateVerificationTokenService.sender(user, updateVerificationToken,
    // verificationLink);
    // }
    // }
    // }

    // @Override
    // public void updateEmail(String reference, String email) {
    // int userId = userService.findUserIdByReference(reference);
    // // check if email is already userd or not
    // try {
    // userService.get(email); // it will throw exception if user not found ...
    // throw new UserExistException("Error: " + email + " is already used");
    // } catch (UserNotFoundException exc) {
    // User user = userService.get(userId);
    // boolean verifyEmail = Miscellaneous.isValidEmail(email);
    // if (user != null && verifyEmail) {
    // // Generate Verification Token
    // UpdateVerificationToken updateVerificationToken =
    // updateVerificationTokenService.generate(
    // userId,
    // email,
    // "email");

    // // send verification email
    // updateVerificationTokenService.sender(user, updateVerificationToken,
    // verificationLink);
    // }
    // }
    // }

    // @Override
    // public void updatePassword(int userId, String oldPassword, String
    // newPassword) {
    // // fetch user information
    // User user = userService.get(userId);
    // if (user != null) {
    // // verify olf password
    // boolean verifyOldPassword = passwordEncoder.matches(oldPassword,
    // user.getPassword());
    // if (verifyOldPassword) {
    // // verify new password
    // if (Miscellaneous.isValidPassword(newPassword)) {
    // // Hash new password ...
    // String encryptedPassword = passwordEncoder.encode(newPassword);
    // user.setPassword(encryptedPassword);
    // // save new password to user
    // userRepository.save(user);
    // } else {
    // throw new WeakPasswordException("Warning: Password is too weak");
    // }
    // } else {
    // throw new WrongPasswordException("Error: Wrong Password");
    // }
    // }
    // }

    // @Override
    // public void updatePassword(String reference, String oldPassword, String
    // newPassword) {
    // int userId = userService.findUserIdByReference(reference);
    // // fetch user information
    // User user = userService.get(userId);
    // if (user != null) {
    // // verify olf password
    // boolean verifyOldPassword = passwordEncoder.matches(oldPassword,
    // user.getPassword());
    // if (verifyOldPassword) {
    // // verify new password
    // if (Miscellaneous.isValidPassword(newPassword)) {
    // // Hash new password ...
    // String encryptedPassword = passwordEncoder.encode(newPassword);
    // user.setPassword(encryptedPassword);
    // // save new password to user
    // userRepository.save(user);
    // } else {
    // throw new WeakPasswordException("Warning: Password is too weak");
    // }
    // } else {
    // throw new WrongPasswordException("Error: Wrong Password");
    // }
    // }
    // }

    // @Override
    // public boolean deactivate(int userId, String password) {
    // User user = userService.get(userId);
    // if (user != null) {
    // boolean verifyPassword = passwordEncoder.matches(password,
    // user.getPassword());
    // if (verifyPassword) {
    // if (user.isActive()) {
    // user.setActive(0);
    // return userRepository.save(user) != null;
    // } else {
    // throw new UserException("Warning: User is already deactivated");
    // }
    // } else {
    // throw new WrongPasswordException("Error: Wrong Password");
    // }
    // }
    // return false;
    // }

    // @Override
    // public boolean deactivate(String reference, String password) {
    // int userId = userService.findUserIdByReference(reference);

    // User user = userService.get(userId);
    // if (user != null) {
    // boolean verifyPassword = passwordEncoder.matches(password,
    // user.getPassword());
    // if (verifyPassword) {
    // if (user.isActive()) {
    // user.setActive(0);
    // return userRepository.save(user) != null;
    // } else {
    // throw new UserException("Warning: User is already deactivated");
    // }
    // } else {
    // throw new WrongPasswordException("Error: Wrong Password");
    // }
    // }
    // return false;
    // }

    // @Override
    // public void delete(int userId) {
    // // NOTE: remove all the data from tables related to this user before removing
    // // the user
    // // removing user addresses
    // User user = userService.get(userId);

    // userRepository.delete(user);
    // }

    // @Override
    // public void delete(String reference) {
    // int userId = userService.findUserIdByReference(reference);
    // // NOTE: remove all the data from tables related to this user before removing
    // // the user
    // // removing user addresses
    // User user = userService.get(userId);
    // userRepository.delete(user);
    // }

    // ----------------------------------------------------------------
    // helper methods
    // ----------------------------------------------------------------

    public void verifyUserDetails(SignInModel signInModel) {
        String name = signInModel.getName();
        String email = signInModel.getEmail();
        String password = signInModel.getPassword();

        // checking user name
        if (name == null || name == "") {
            throw new UserException("Error: Invalid user name");
        }

        // checking user email
        if (email == null || email == "") {
            throw new InvalidEmailException("Error: Empty email address Error");
        } else if (!Miscellaneous.isValidEmail(email)) {
            throw new InvalidEmailException("Error: Invalid email Address");
        } else if (userService.existsByEmailAndVerified(email)) {
            throw new UserExistException("Warning: Email already exists, try login !!!");
        }

        // checking user password
        if (password == null || password.isEmpty()) {
            throw new InvalidPasswordException("Error: Invalid password Error");
        } else if (!Miscellaneous.isStrongPassword(password)) {
            throw new InvalidPasswordException("Error: Weak password Error");
        }
    }

}
