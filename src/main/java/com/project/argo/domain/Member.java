package com.project.argo.domain;

import com.project.argo.domain.team.MapMemberTeam;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "member",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<MapMemberTeam> teams = new ArrayList<>();

    @Builder
    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
