package com.example.miniproject.dto.request.employee;

import java.time.LocalTime;

public class EmployeeLeaveRequest {

    private Long employeeId;
    private LocalTime endTime = LocalTime.now();

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
