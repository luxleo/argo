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
    
    // FIXME: status field만 setter를 두었는데 빌더 패턴으로 풀 수있는 방법은 없는지 확인하기
    public void setStatus(WorkStatus status) {
        this.status = status;
    }

    @Builder
    public Task(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.status = WorkStatus.WORKING;
    }


    public Task.TaskBuilder toBuilder() {
        return Task.builder()
                .name(this.name)
                .desc(this.desc);
    }
    public void updateTask(Task toUpdate) {
        this.name = toUpdate.getName();
        this.desc = toUpdate.getDesc();
        this.status = toUpdate.getStatus();
    }
}
