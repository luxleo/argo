package com.project.argo.domain.team.project;

import com.project.argo.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Position {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "position_id")
    private Long id;
    /*
     * category mapping 해주기
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Project project;

    @OneToMany(fetch = FetchType.LAZY,orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Job> jobs = new ArrayList<>();
}
