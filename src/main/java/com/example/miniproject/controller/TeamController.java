package com.example.miniproject.controller;

import com.example.miniproject.domain.team.Team;
import com.example.miniproject.domain.team.TeamRepository;
import com.example.miniproject.dto.request.team.TeamCreateRequest;
import com.example.miniproject.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    TeamService teamService;

    public TeamController(TeamService companyService) {
        this.teamService = companyService;
    }

    @PostMapping()
    public void enrollTeam(@RequestBody TeamCreateRequest request) {
        teamService.enrollTeam(request);
    }

    @GetMapping()
    public List<Team> findAllTeam() {
        return teamService.findAllTeam();
    }
    // 위의 방법도 괜찮고 사실 List<TeamReadResponse> 형을 반환하는 것이 이상적이긴 함.

}
