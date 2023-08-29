package com.project.argo.domain.team.project.work;

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
public class Stage {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "stage_id")
    private Long id;
    @Column(name = "stage_name")
    private String name;
    @Enumerated(EnumType.STRING)
    private WorkStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Project project;

    @OneToMany(mappedBy ="stage",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

    public void setStatus(WorkStatus status) {
        this.status = status;
    }

    public void allocateProject(Project project) {
        this.project = project;
    }
    public void addJob(Job job) {
        this.jobs.add(job);
        job.allocateStage(this);
    }

    public void updateStage(Stage toUpdate) {
        this.name = toUpdate.getName();
        this.status = toUpdate.getStatus();
    }
    @Builder
    public Stage(String name) {
        this.name = name;
        this.status = WorkStatus.WORKING;
    }
}
