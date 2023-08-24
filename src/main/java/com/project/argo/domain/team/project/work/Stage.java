package com.project.argo.domain.team.project.work;

import com.project.argo.domain.team.project.Project;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private WorkStatus stageStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Project project;

    @OneToMany(mappedBy ="stage",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();
}
