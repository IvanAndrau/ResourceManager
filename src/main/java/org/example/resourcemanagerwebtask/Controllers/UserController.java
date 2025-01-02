package org.example.resourcemanagerwebtask.Controllers;

import org.example.resourcemanagerwebtask.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userDashboard")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String userDashboard(Model model) {
        //model.addAttribute("users", userService.getAllUsers());
        return "userDashboard";
    }

}