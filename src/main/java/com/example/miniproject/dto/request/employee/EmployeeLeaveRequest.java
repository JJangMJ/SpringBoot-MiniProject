package com.example.miniproject.dto.request.employee;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmployeeLeaveRequest {

    private Long employeeId;
    private LocalDateTime endTime = LocalDateTime.now();

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
