package com.project.argo.domain.team;

import com.project.argo.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class MapMemberTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @Column(name = "join_date")
    private LocalDate joinedDate;

    public void createMapping(Member reqMember, Team reqTeam) {
        this.member = reqMember;
        reqMember.getTeams().add(this);

        this.team = reqTeam;
        reqTeam.getMembers().add(this);

        this.joinedDate = LocalDate.now();
    }
}
