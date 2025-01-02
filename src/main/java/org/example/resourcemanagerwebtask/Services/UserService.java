package org.example.resourcemanagerwebtask.Services;

import org.example.resourcemanagerwebtask.Helpers.SecurityManager;
import org.example.resourcemanagerwebtask.Models.User;
import org.example.resourcemanagerwebtask.Models.Admin;
import org.example.resourcemanagerwebtask.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean updatePassword(Long userId, String newPassword) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setPassword(hashPassword(newPassword));
            userRepository.save(existingUser);
            return true;
        }
        return false;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }



    public boolean registerUser(String username, String email, String password, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }

        User user;
        if ("ADMIN".equalsIgnoreCase(role)) {
            user = new Admin(username, email, hashPassword(password));
        } else {
            user = new User(username, email, hashPassword(password));
        }

        userRepository.save(user);
        return true;
    }

    public Optional<User> loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(hashPassword(password))) {
            return user;
        }
        return Optional.empty();
    }

    public boolean isAdmin(String username) {
        String role = userRepository.findRoleByUsername(username);
        return "admin".equalsIgnoreCase(role);
    }

    public boolean isRegularUser(String username) {
        String role = userRepository.findRoleByUsername(username);
        return "user".equalsIgnoreCase(role);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
