package com.azbugbusters.institute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin")
    public String adminDashboard() {
        return "dashboards/admin";
    }

    @GetMapping("/faculty")
    public String facultyDashboard() {
        return "dashboards/faculty";
    }

    @GetMapping("/student")
    public String studentDashboard() {
        return "dashboards/student";
    }
}


