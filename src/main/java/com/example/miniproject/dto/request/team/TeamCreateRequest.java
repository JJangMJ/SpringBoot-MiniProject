package com.example.miniproject.dto.request.team;

public class TeamCreateRequest {

    private String name;
    private String manager = "";
    private int memberCount = 0;

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public int getMemberCount() {
        return memberCount;
    }
}
