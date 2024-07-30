package com.example.miniproject.controller;

import com.example.miniproject.domain.employee.Employee;
import com.example.miniproject.dto.request.employee.EmployeeCreateRequest;
import com.example.miniproject.dto.request.employee.EmployeeGetRequest;
import com.example.miniproject.dto.request.employee.EmployeeGoRequest;
import com.example.miniproject.dto.request.employee.EmployeeLeaveRequest;
import com.example.miniproject.dto.response.employee.EmployeeMonthStatusResponse;
import com.example.miniproject.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService companyService) {
        this.employeeService = companyService;
    }

    @GetMapping()
    public List<Employee> findAllEmployee() {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/monthStatus")
    public EmployeeMonthStatusResponse monthStatus(EmployeeGetRequest request) {
        return employeeService.getMonthStatus(request);
    }

    @PostMapping()
    public void enrollEmployee(@RequestBody EmployeeCreateRequest request) {
        employeeService.enrollEmployee(request);
    }

    @PostMapping("/go")
    public void goWork(@RequestBody EmployeeGoRequest request) {
        employeeService.goWork(request);
    }

    @PutMapping("/leave")
    public void leaveWork(@RequestBody EmployeeLeaveRequest request) {
        employeeService.leaveWork(request);
    }
}
