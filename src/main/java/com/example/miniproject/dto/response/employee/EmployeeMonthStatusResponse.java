package com.example.miniproject.dto.response.employee;


import com.example.miniproject.domain.employee.MonthStatus;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMonthStatusResponse {

    private List<MonthStatus> detail = new ArrayList<>();
    private int sum;

    public EmployeeMonthStatusResponse(List<MonthStatus> detail, int sum) {
        this.detail = detail;
        this.sum = sum;
    }

    public List<MonthStatus> getDetail() {
        return detail;
    }

    public int getSum() {
        return sum;
    }
}
