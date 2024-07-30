package com.example.miniproject.domain.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class CommuteStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long workingMinutes;
    private boolean status = false;

    public CommuteStatus() {

    }

    public CommuteStatus(Long employeeId, LocalDateTime startTime, LocalDateTime endTime, boolean status) {
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public boolean getStatus() {
        return status;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    public void changeStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void changeStatus(boolean status) {
        this.status = status;
    }

    public long getWorkingMinutes() {
        return workingMinutes;
    }

    public void changeWorkingMinutes(long workingMinutes) {
        this.workingMinutes += workingMinutes;
    }
}
