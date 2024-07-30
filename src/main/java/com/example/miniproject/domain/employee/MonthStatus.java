package com.example.miniproject.domain.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class MonthStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    private int workingMinutes;

    public MonthStatus() {
    }

    public MonthStatus(LocalDate date, int workingMinutes) {
        this.date = date;
        this.workingMinutes = workingMinutes;
    }

    public void changeWorkingMinutes(int minutes) {
        this.workingMinutes += minutes;
    }

}
