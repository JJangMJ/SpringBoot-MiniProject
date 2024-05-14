package com.example.miniproject.domain.team;

import jakarta.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String manager;

    private int memberCount;

    public Team() {
    }

    public Team(String name, String manager, int memberCount) {
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

    public void updateMemberCount() {
        this.memberCount += 1;
    }

    public void updateManager(String manager) {
        this.manager = manager;
    }
}
