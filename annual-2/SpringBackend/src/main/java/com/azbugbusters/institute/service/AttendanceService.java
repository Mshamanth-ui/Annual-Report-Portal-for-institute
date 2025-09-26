package com.azbugbusters.institute.service;

import java.time.LocalDate;

public interface AttendanceService {
    void markAttendance(String studentId, LocalDate date, boolean present);
}


