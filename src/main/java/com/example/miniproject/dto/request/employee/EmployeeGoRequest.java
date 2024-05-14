package com.example.miniproject.dto.request.employee;

import java.time.LocalTime;

public class EmployeeGoRequest {

    private Long employeeId;
    private LocalTime startTime = LocalTime.now();
    private LocalTime endTime = null;

    public LocalTime getEndTime() {
        return endTime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
}
