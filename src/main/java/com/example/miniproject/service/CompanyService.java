package com.example.miniproject.service;

import com.example.miniproject.domain.employee.CommuteStatus;
import com.example.miniproject.domain.employee.CommuteStatusRepository;
import com.example.miniproject.domain.employee.Employee;
import com.example.miniproject.domain.employee.EmployeeRepository;
import com.example.miniproject.domain.team.Team;
import com.example.miniproject.domain.team.TeamRepository;
import com.example.miniproject.dto.request.employee.EmployeeCreateRequest;
import com.example.miniproject.dto.request.employee.EmployeeGoRequest;
import com.example.miniproject.dto.request.employee.EmployeeLeaveRequest;
import com.example.miniproject.dto.request.team.TeamCreateRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class CompanyService {

    TeamRepository teamRepository;
    EmployeeRepository employeeRepository;
    CommuteStatusRepository commuteStatusRepository;

    public CompanyService(TeamRepository teamRepository, EmployeeRepository employeeRepository, CommuteStatusRepository commuteStatusRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.commuteStatusRepository = commuteStatusRepository;
    }

    public void enrollTeam(TeamCreateRequest request) {
        Team team = new Team(request.getName(), request.getManager(), request.getMemberCount());
        teamRepository.save(team);
    }

    public void enrollEmployee(EmployeeCreateRequest request) {

        if (teamRepository.existsByName(request.getTeamName()) == false) {
            throw new IllegalArgumentException("그런 팀은 없습니다.");
        } else {
            Employee employee = new Employee(request.getName(), request.getTeamName(), request.getRole(), request.getBirthday(), request.getWorkStartDate());

            Team team = teamRepository.findByName(request.getTeamName());

            if (employee.getRole().equals("MANAGER") && teamRepository.findByName(request.getTeamName()).getManager().equals("")) {
                team.updateManager(request.getName());
                team.updateMemberCount();
                teamRepository.save(team);
                employeeRepository.save(employee);
            } else if (employee.getRole().equals("MANAGER") && teamRepository.findByName(request.getTeamName()).getManager().equals("MANAGER")) {
                throw new IllegalArgumentException("이미 MANAGER가 있습니다.");
            } else if (employee.getRole().equals("MEMBER")) {
                team.updateMemberCount();
                teamRepository.save(team);
                employeeRepository.save(employee);
            }
        }
    }

    public void goToWork(EmployeeGoRequest request) {
        if (employeeRepository.existsById(request.getEmployeeId()) == true && commuteStatusRepository.existsByEmployeeId(request.getEmployeeId()) == false) {
            CommuteStatus commuteStatus = new CommuteStatus(request.getEmployeeId(), request.getStartTime(), request.getEndTime(), true);
            commuteStatusRepository.save(commuteStatus);
        } else if (employeeRepository.existsById(request.getEmployeeId()) == true && commuteStatusRepository.existsByEmployeeId(request.getEmployeeId()) == true) {
            CommuteStatus commuteStatus = commuteStatusRepository.findById(request.getEmployeeId()).orElse(null);
            if (commuteStatus != null) {
                commuteStatus.changeStartTime(request.getStartTime());
                commuteStatus.changeStatus(true);
                commuteStatus.setEndTime(null);
                commuteStatusRepository.save(commuteStatus);
            }
        } else {
            throw new IllegalArgumentException("이런 아이디를 가진 직원은 없습니다. 등록을 먼저 해주세요.");
        }
    }

    public void getOffWork(EmployeeLeaveRequest request) {

        if (commuteStatusRepository.existsByEmployeeId(request.getEmployeeId()) == true) {
            CommuteStatus commuteStatus = commuteStatusRepository.findByEmployeeId(request.getEmployeeId());
            if (commuteStatus != null) {
                commuteStatus.setEndTime(request.getEndTime());
                commuteStatus.changeStatus(false);
                commuteStatus.changeWorkingMinutes(Duration.between(commuteStatus.getStartTime(), request.getEndTime()).toMinutes());
                commuteStatusRepository.save(commuteStatus);
            }
        } else {
            throw new IllegalArgumentException("출근하지 않은 직원입니다.");
        }

    }

    public List<Team> findAllTeam() {
        List<Team> teams = teamRepository.findAll();
        return teams;
    }
    // 위의 방법도 괜찮지만 만약 List<TeamReadResponse> 형이었으면
    // return users.stream()
    //             .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
    //             .collect(Collectors.toList());
    // 이렇게 반환했어야 함. 사실 이게 이상적임.

    public List<Employee> findAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
}
