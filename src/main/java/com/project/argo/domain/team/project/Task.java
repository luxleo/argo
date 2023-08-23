package com.project.argo.domain.team.project;

import jakarta.persistence.*;
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
}
