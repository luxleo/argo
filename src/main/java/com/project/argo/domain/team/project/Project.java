package com.project.argo.domain.team.project;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.Team;
import com.project.argo.domain.team.project.recruit.ProjectGroup;
import com.project.argo.domain.team.project.work.Stage;
import jakarta.persistence.*;
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
    private List<ProjectGroup> groups;
}
