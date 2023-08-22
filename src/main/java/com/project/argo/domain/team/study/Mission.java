package com.project.argo.domain.team.study;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class Mission {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "mission_id")
    private Long id;
    private String content;
    private int order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
    }
}
