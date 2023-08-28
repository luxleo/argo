package com.project.argo.domain.team.project.recruit;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.project.work.Job;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * project에 member,position을 매핑 시켜주는 엔티티이다.
 */
@Entity
@Getter
@NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_group_id")
    private ProjectGroup group;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "worker")
    private List<Job> jobs = new ArrayList<>();

    public void allocateGroup(ProjectGroup group) {
        this.group = group;
    }

    public Role(Member member) {
        this.member = member;
    }
}
