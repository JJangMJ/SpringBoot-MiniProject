package com.example.miniproject.domain.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class CommuteStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private LocalTime startTime;
    private LocalTime endTime;
    private long workingMinutes;
    private boolean status = false;

    public CommuteStatus() {

    }

    public CommuteStatus(Long employeeId, LocalTime startTime, LocalTime endTime, boolean status) {
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public LocalTime getStartTime() {
        return startTime;
    }


    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


    public void changeStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void changeStatus(boolean status) {
        this.status = status;
    }

    public void changeWorkingMinutes(long workingMinutes) {
        this.workingMinutes += workingMinutes;
    }
}
