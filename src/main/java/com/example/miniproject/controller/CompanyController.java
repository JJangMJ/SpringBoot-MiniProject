package com.example.miniproject.controller;

import com.example.miniproject.domain.employee.Employee;
import com.example.miniproject.domain.team.Team;
import com.example.miniproject.dto.request.employee.EmployeeCreateRequest;
import com.example.miniproject.dto.request.employee.EmployeeGoRequest;
import com.example.miniproject.dto.request.employee.EmployeeLeaveRequest;
import com.example.miniproject.dto.request.team.TeamCreateRequest;
import com.example.miniproject.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/team")
    public void enrollTeam(@RequestBody TeamCreateRequest request){
        companyService.enrollTeam(request);
    }

    @PostMapping("/employee")
    public void enrollEmployee(@RequestBody EmployeeCreateRequest request){
        companyService.enrollEmployee(request);
    }

    @PostMapping("/employee/go")
    public void goToWork(@RequestBody EmployeeGoRequest request) {
        companyService.goToWork(request);
    }

    @PutMapping("/employee/leave")
    public void getOffWork(@RequestBody EmployeeLeaveRequest request) {
        companyService.getOffWork(request);
    }


    @GetMapping("/team")
    public List<Team> findAllTeam() {
        return companyService.findAllTeam();
    }
    // 위의 방법도 괜찮고 사실 List<TeamReadResponse> 형을 반환하는 것이 이상적이긴 함.

    @GetMapping("/employee")
    public List<Employee> findAllEmployee() {
        return companyService.findAllEmployee();
    }

}
