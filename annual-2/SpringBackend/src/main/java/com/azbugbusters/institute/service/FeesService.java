package com.azbugbusters.institute.service;

import java.math.BigDecimal;

public interface FeesService {
    boolean initiatePayment(String studentId, String term, BigDecimal amount);
}


