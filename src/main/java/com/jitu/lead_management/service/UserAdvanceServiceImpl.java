package com.jitu.lead_management.service;

import org.springframework.stereotype.Service;

@Service
public class UserAdvanceServiceImpl implements UserAdvanceService {

    // @Autowired
    // private UserService userService;
    // @Autowired
    // private PasswordEncoder passwordEncoder;
    // @Autowired
    // private VerificationTokenService verificationTokenService;
    // @Autowired
    // private VerificationService verificationService;
    // @Autowired
    // private UpdateVerificationTokenService updateVerificationTokenService;

    // private static final String verificationLink =
    // "http://localhost:8080/api/users/verify-update?token=";

    // private static final Logger logger =
    // LoggerFactory.getLogger(UserAdvanceServiceImpl.class);

    // ----------------------------------------------------------------
    // service methods for user
    // ----------------------------------------------------------------

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

}
