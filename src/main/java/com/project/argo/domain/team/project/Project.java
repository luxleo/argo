package com.project.argo.domain.team.project;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.Team;
import com.project.argo.domain.team.project.recruit.ProjectGroup;
import com.project.argo.domain.team.project.recruit.RecruitStatus;
import com.project.argo.domain.team.project.work.Stage;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("project") //TODO: 아마 멤버별 포지션이 있을 것이다.
public class Project extends Team {
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stage> stages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member leader;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProjectGroup> groups = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RecruitStatus recruitStatus;

    @OneToOne(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
    private ProjectInfo projectInfo;
    
    //TODO: 이거 ellipses로 처리하는게 나은지 잘 모르겠음
    public void addGroup(ProjectGroup projectGroup) {
        this.groups.add(projectGroup);
        projectGroup.allocateProject(this);
    }
    public void allocateInfo(ProjectInfo projectInfo) {

        this.projectInfo = projectInfo;
        projectInfo.allocateProject(this);
    }

    @Builder
    public Project(String name, Member leader,ProjectInfo projectInfo) {
        super(name);
        this.leader = leader;
        this.recruitStatus = RecruitStatus.HIRING;
        this.projectInfo = projectInfo;
    }
}
