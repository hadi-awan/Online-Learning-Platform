package com.hadi.online_learning_platform.service;

import com.hadi.online_learning_platform.model.User;
import com.hadi.online_learning_platform.respository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    /*
    Implementation of UserService to manage user registration and authentication
     */

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public User registerUser(User user) {
        // Check if the username or email already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already taken");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already registered");
        }

        // Encode the user's password before saving it to the database
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Set default role (e.g., ROLE_USER)
        user.setRole("USER");

        return userRepository.save(user);
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("User not found"));

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException("Invalid password");
        }

        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("User not found"));
    }
}
