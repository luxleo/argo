package com.project.argo.domain.team.project.work;

import com.project.argo.domain.team.project.recruit.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "job_id")
    private Long id;
    private String name;
    /*
    TODO: 담당자 position으로 해야되는데 member를 어떻게 매핑하지.... => M:N관계 풀어 내듯이 풀어 냈다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role worker;

    //TODO: work status를 어떻게 갱신할 것인가?:task가 여러개인 경우 -> 모든 task를 완료했을때, task가 0개일때 -> 가능하게 할까 말까
    @Enumerated(EnumType.STRING)
    private WorkStatus status;

    @OneToMany(mappedBy = "job", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public void allocateWorker(Role worker) {
        this.worker = worker;
    }

}
