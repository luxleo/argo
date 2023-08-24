package com.project.argo.domain.team.project.recruit;

import com.project.argo.domain.team.project.Project;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ProjectGroup {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_group_id")
    private Long id;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Project project;

    @Column(name = "max_to_num")
    private Integer maxTO;

}
