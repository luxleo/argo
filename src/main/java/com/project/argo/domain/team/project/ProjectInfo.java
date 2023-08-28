package com.project.argo.domain.team.project;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class ProjectInfo {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_info_id")
    private Long id;

    private String desc;

    @OneToOne
    private Project project;
    //TODO: project picture

    public void allocateProject(Project project) {
        this.project = project;
    }

    @Builder
    public ProjectInfo(String desc) {
        this.desc = desc;
    }
}
