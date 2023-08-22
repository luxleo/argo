package com.project.argo.domain.team.study;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "course_id")
    private Long id;
    private String name;
    private String desc;

    @OneToMany(mappedBy ="course",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Mission> missions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Study study;

    private LocalDateTime start;
    private LocalDateTime end;

    public void setStudy(Study study) {
        this.study = study;
    }

    public void addMission(Mission mission) {
        getMissions().add(mission);
        mission.setCourse(this);
    }
}
