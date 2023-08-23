package com.project.argo.domain.team.study;

import com.project.argo.request.team.study.CourseUpdateRequest;
import jakarta.persistence.*;
import lombok.Builder;
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

    @OneToMany(mappedBy ="course",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Mission> missions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Study study;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public void setStudy(Study study) {
        this.study = study;
    }

    public void addMission(Mission mission) {
        getMissions().add(mission);
        mission.setCourse(this);
    }

    @Builder
    public Course(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public void updateWithDto(CourseUpdateRequest dto) {
        this.name = dto.getName();
        this.desc = dto.getDesc();
    }
}
