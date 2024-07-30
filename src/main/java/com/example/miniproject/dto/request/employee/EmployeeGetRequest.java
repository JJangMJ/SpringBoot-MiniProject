package com.example.miniproject.dto.request.employee;

import java.time.LocalDateTime;

public class EmployeeGetRequest {

    private Long employeeId;
    private LocalDateTime date;

    public EmployeeGetRequest(Long employeeId, LocalDateTime date) {
        this.employeeId = employeeId;
        this.date = date;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
