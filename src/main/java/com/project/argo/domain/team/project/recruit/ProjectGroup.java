package com.project.argo.domain.team.project.recruit;

import com.project.argo.domain.team.project.Project;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ProjectGroup {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_group_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Project project;

    @Column(name = "max_to_num")
    private Integer maxTO;
    private int currentUserNum;

    @Enumerated(EnumType.STRING)
    private RecruitStatus recruitStatus;

    public void allocateProject(Project project) {
        this.project = project;
    }
    public void addRoles(Role role) {
        //TODO: currentUserNUm과 maxTo 비교하는 validation 로직이 필요할 것이다.
        this.roles.add(role);
        role.allocateGroup(this);
        this.currentUserNum++;
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }
    @Builder
    public ProjectGroup(Position position,Integer maxTO) {
        this.position = position;
        this.maxTO = maxTO;
        this.recruitStatus = RecruitStatus.HIRING;
    }
}
