package com.example.miniproject.dto.request.employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeCreateRequest {

    private String name;
    private String teamName;
    private String role;
    private LocalDateTime birthday;
    private LocalDateTime workStartDate;

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public LocalDateTime getWorkStartDate() {
        return workStartDate;
    }
}
