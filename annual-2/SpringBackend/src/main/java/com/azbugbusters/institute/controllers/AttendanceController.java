package com.azbugbusters.institute.controllers;

import com.azbugbusters.institute.service.AttendanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/faculty/attendance")
    @PreAuthorize("hasRole('FACULTY') or hasRole('ADMIN')")
    public String facultyAttendanceForm() {
        return "modules/attendance-faculty";
    }

    @GetMapping("/student/attendance-view")
    public String studentAttendanceView(Model model) {
        List<String> rows = new ArrayList<>();
        rows.add("2025-01-10: Present");
        rows.add("2025-01-11: Absent");
        model.addAttribute("rows", rows);
        return "modules/attendance-student";
    }

    @PostMapping("/faculty/attendance/mark")
    @PreAuthorize("hasRole('FACULTY') or hasRole('ADMIN')")
    public String mark(@RequestParam String studentId, @RequestParam String date, @RequestParam boolean present) {
        attendanceService.markAttendance(studentId, LocalDate.parse(date), present);
        return "redirect:/faculty";
    }
}


