package com.azbugbusters.institute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ModuleControllers {

    // Admin routes
    @GetMapping("/admin/admit-card")
    public String adminAdmitCard() { return "modules/admit-card"; }

    @GetMapping("/admin/fees")
    public String adminFees() { return "modules/fees"; }

    @GetMapping("/admin/attendance")
    public String adminAttendance() { return "modules/attendance"; }

    @GetMapping("/admin/finance")
    public String adminFinance() { return "modules/finance"; }

    @GetMapping("/admin/reports")
    public String adminReports() { return "modules/reports"; }

    // Faculty routes
    @GetMapping("/faculty/attendance")
    public String facultyAttendance() { return "modules/attendance"; }

    @GetMapping("/faculty/reports")
    public String facultyReports() { return "modules/reports"; }

    // Student routes
    @GetMapping("/student/admit-card")
    public String studentAdmitCard() { return "modules/admit-card"; }

    @GetMapping("/student/fees")
    public String studentFees() { return "modules/fees"; }

    @GetMapping("/student/attendance")
    public String studentAttendance() { return "modules/attendance"; }

    @GetMapping("/student/reports")
    public String studentReports() { return "modules/reports"; }
}


