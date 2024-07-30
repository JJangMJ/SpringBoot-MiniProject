package com.example.miniproject.service;

import com.example.miniproject.domain.employee.CommuteStatusRepository;
import com.example.miniproject.domain.employee.EmployeeRepository;
import com.example.miniproject.domain.team.Team;
import com.example.miniproject.domain.team.TeamRepository;
import com.example.miniproject.dto.request.team.TeamCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    TeamRepository teamRepository;
    EmployeeRepository employeeRepository;
    CommuteStatusRepository commuteStatusRepository;

    public TeamService(TeamRepository teamRepository, EmployeeRepository employeeRepository, CommuteStatusRepository commuteStatusRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.commuteStatusRepository = commuteStatusRepository;
    }

    public void enrollTeam(TeamCreateRequest request) {
        Team team = new Team(request.getName(), request.getManager(), request.getMemberCount());
        teamRepository.save(team);
    }

    public List<Team> findAllTeam() {
        return teamRepository.findAll();
    }
    // 위의 방법도 괜찮지만 만약 List<TeamReadResponse> 형이었으면
    // return users.stream()
    //             .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
    //             .collect(Collectors.toList());
    // 이렇게 반환했어야 함. 사실 이게 이상적임
}
