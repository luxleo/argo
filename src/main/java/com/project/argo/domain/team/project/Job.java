package com.project.argo.domain.team.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    /*
    TODO: 담당자 position으로 해야되는데 member를 어떻게 매핑하지.... => M:N관계 풀어 내듯이 풀어 냈다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position worker;

    @Enumerated(EnumType.STRING)
    private WorkStatus status;
}
