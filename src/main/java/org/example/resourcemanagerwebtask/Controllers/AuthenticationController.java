package org.example.resourcemanagerwebtask.Controllers;

import jakarta.servlet.http.HttpSession;
import org.example.resourcemanagerwebtask.Models.User;
import org.example.resourcemanagerwebtask.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
            public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {
        boolean success = userService.registerUser(username, email, password, role);
        if (!success) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }
        else {
            model.addAttribute("success", "User registered successfully.");
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        Optional<User> user = userService.loginUser(username, password);

        if (user.isPresent()) {
            User loggedInUser = user.get();
            session.setAttribute("loggedInUser", user.get());

            String role = loggedInUser.getRole();
            if ("admin".equalsIgnoreCase(role)) {
                return "redirect:/adminDashboard";
            } else if ("user".equalsIgnoreCase(role)) {
                return "redirect:/userDashboard";
            }

            return "redirect:/home";
        }

        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

}