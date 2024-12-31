package org.example.resourcemanagerwebtask.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }

    @GetMapping("/userDashboard")
    public String userDashboard() {
        return "userDashboard";
    }
}