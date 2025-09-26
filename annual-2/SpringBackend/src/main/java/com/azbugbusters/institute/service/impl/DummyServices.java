package com.azbugbusters.institute.service.impl;

import com.azbugbusters.institute.service.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class DummyServices implements AdmitCardService, FeesService, AttendanceService, FinanceService, ReportService {
    @Override
    public boolean verifyAdmitCard(String admitCardNumber) {
        return admitCardNumber != null && admitCardNumber.trim().length() >= 6;
    }

    @Override
    public boolean initiatePayment(String studentId, String term, BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public void markAttendance(String studentId, LocalDate date, boolean present) {
        // no-op dummy
    }

    @Override
    public BigDecimal getTotalRevenue() {
        return new BigDecimal("0.00");
    }

    @Override
    public BigDecimal getOutstandingDues() {
        return new BigDecimal("0.00");
    }

    @Override
    public List<String> listAvailableYears() {
        return Arrays.asList("2022-2023", "2023-2024", "2024-2025");
    }
}


