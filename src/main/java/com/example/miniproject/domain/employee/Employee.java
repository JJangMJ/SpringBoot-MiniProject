package com.example.miniproject.domain.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String teamName;
    private String role;
    private LocalDateTime birthday;
    private LocalDateTime workStartDate;

    public Employee() {
    }

    public Employee(String name, String teamName, String role, LocalDateTime birthday, LocalDateTime workStartDate) {
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public LocalDateTime getWorkStartDate() {
        return workStartDate;
    }

    public String getRole() {
        return role;
    }
}
