package com.jitu.lead_management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.exception.UserNotFoundException;
import com.jitu.lead_management.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public int findUserIdByEmail(String email) {
        return userRepository.findUserIdByEmail(email).orElse(0);
    }

    @Override
    public User get(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User get(String email) {

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("Error: User not found by Email: " + email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String getUserName(int userId) {
        return userRepository.findNameByUserId(userId).orElse("unknown");
    }

    @Override
    public boolean isVerified(int userId) {
        return userRepository.existsByUserIdAndVerified(userId, 1);
    }

    @Override
    public Boolean existsByEmailAndVerified(String email) {
        return userRepository.existsByEmailAndVerified(email, 1);
    }

    @Override
    public Boolean existsByUserIdAndVerified(int userId) {
        return userRepository.existsByUserIdAndVerified(userId, 1);
    }

    @Override
    public Boolean existsByEmailAndNotVerified(String email) {
        return userRepository.existsByEmailAndVerified(email, 0);
    }
}