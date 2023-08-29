package com.project.argo.domain.team.project.work;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "task_id")
    private Long id;

    private String name;
    private String desc;

    @Enumerated(EnumType.STRING)
    private WorkStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    public void allocateJob(Job job) {
        this.job = job;
    }
    @Builder
    public Task(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.status = WorkStatus.WORKING;
    }
}
