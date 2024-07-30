package com.example.miniproject.dto.request.employee;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmployeeGoRequest {

    private Long employeeId;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime = null;

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
