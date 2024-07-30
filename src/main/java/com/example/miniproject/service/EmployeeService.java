package com.example.miniproject.service;

import com.example.miniproject.domain.employee.*;
import com.example.miniproject.domain.team.Team;
import com.example.miniproject.domain.team.TeamRepository;
import com.example.miniproject.dto.request.employee.EmployeeCreateRequest;
import com.example.miniproject.dto.request.employee.EmployeeGetRequest;
import com.example.miniproject.dto.request.employee.EmployeeGoRequest;
import com.example.miniproject.dto.request.employee.EmployeeLeaveRequest;
import com.example.miniproject.dto.response.employee.EmployeeMonthStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    TeamRepository teamRepository;
    EmployeeRepository employeeRepository;
    CommuteStatusRepository commuteStatusRepository;
    MonthStatusRepository monthStatusRepository;
    List<MonthStatus> todayWorkTimeList = new ArrayList<>();

    public EmployeeService(TeamRepository teamRepository,
                           EmployeeRepository employeeRepository,
                           CommuteStatusRepository commuteStatusRepository,
                           MonthStatusRepository monthStatusRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.commuteStatusRepository = commuteStatusRepository;
        this.monthStatusRepository = monthStatusRepository;
    }

    public void enrollEmployee(EmployeeCreateRequest request) {

        if (!teamRepository.existsByName(request.getTeamName())) {
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

    public void goWork(EmployeeGoRequest request) {

        if (employeeRepository.existsById(request.getEmployeeId()) && !commuteStatusRepository.existsByEmployeeId(request.getEmployeeId())) {
            CommuteStatus commuteStatus = new CommuteStatus(request.getEmployeeId(), request.getStartTime(), request.getEndTime(), true);
            commuteStatusRepository.save(commuteStatus);

        } else if (employeeRepository.existsById(request.getEmployeeId()) && commuteStatusRepository.existsByEmployeeId(request.getEmployeeId())) {

            CommuteStatus commuteStatus = commuteStatusRepository.findById(request.getEmployeeId()).orElse(null);

            if (commuteStatus != null && !commuteStatus.getStatus()) {
                commuteStatus.changeStartTime(request.getStartTime());
                commuteStatus.changeStatus(true);
                commuteStatus.setEndTime(null);
                commuteStatusRepository.save(commuteStatus);
                //throw new IllegalArgumentException("오늘은 이미 퇴근했습니다. 내일 출근해주세요.");
            } else {
                throw new IllegalArgumentException("이미 출근했습니다.");
            }
        } else {
            throw new IllegalArgumentException("없는 직원입니다.");
        }
    }

    public void leaveWork(EmployeeLeaveRequest request) {

        if (commuteStatusRepository.existsByEmployeeId(request.getEmployeeId())) {

            CommuteStatus commuteStatus = commuteStatusRepository.findByEmployeeId(request.getEmployeeId());
//            MonthStatus monthStatus = monthStatusRepository.

            if (commuteStatus != null && commuteStatus.getStatus()) {

                long todayWorkTime = Duration.between(commuteStatus.getStartTime(), request.getEndTime()).toMinutes();

                commuteStatus.setEndTime(request.getEndTime());
                commuteStatus.changeStatus(false);
                commuteStatusRepository.save(commuteStatus);

//                todayWorkTimeList.;
            } else {
                throw new IllegalArgumentException("출근하지 않은 직원입니다.");
            }
        } else {
            if (employeeRepository.existsById(request.getEmployeeId())) {
                throw new IllegalArgumentException("출근하지 않은 직원입니다.");

            } else {
                throw new IllegalArgumentException("없는 직원입니다.");
            }
        }

    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    public EmployeeMonthStatusResponse getMonthStatus(EmployeeGetRequest request) {
        //TODO : 파라미터 넣어주기
        EmployeeMonthStatusResponse response = new EmployeeMonthStatusResponse();
    }
}
