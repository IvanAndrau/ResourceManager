package org.example.resourcemanagerwebtask.Controllers;

import org.example.resourcemanagerwebtask.Models.User;
import org.example.resourcemanagerwebtask.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adminDashboard")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "adminDashboard";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String password,
                             Model model) {
        User newUser = new User(username, email, password);
        userService.saveUser(newUser);
        model.addAttribute("success", "User created successfully.");
        return "redirect:/adminDashboard";
    }

    @GetMapping("/findUser")
    public String findUser(@RequestParam String username, Model model) {
        Optional<User> user = userService.findByUsername(username);
        if (user != null) {
            model.addAttribute("foundUser", user);
        } else {
            model.addAttribute("error", "User not found.");
        }
        return "adminDashboard";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam Long userId,
                                 @RequestParam String newPassword,
                                 Model model) {
        boolean success = userService.updatePassword(userId, newPassword);
        if (success) {
            model.addAttribute("success", "Password updated successfully.");
        } else {
            model.addAttribute("error", "Failed to update password.");
        }
        return "redirect:/adminDashboard";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId, Model model) {
        userService.deleteUser(userId);
        model.addAttribute("success", "User deleted successfully.");
        return "redirect:/adminDashboard";
    }
}
