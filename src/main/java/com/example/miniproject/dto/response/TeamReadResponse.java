package com.example.miniproject.dto.response;

public class TeamReadResponse {

    private String name;
    private String manager;
    private int memberCount;

    public TeamReadResponse(String name, String manager, int memberCount) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
    }

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
