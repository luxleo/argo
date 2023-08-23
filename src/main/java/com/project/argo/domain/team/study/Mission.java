package com.project.argo.domain.team.study;

import com.project.argo.request.team.study.MissionUpdateRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class Mission {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "mission_id")
    private Long id;
    private String content;
    private int nth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
    }
    public Long getCourseId() {
        Long res = null;
        if (this.course != null) {
            res = this.course.getId();
        }
        return res;
    }

    @Builder
    public Mission(String content) {
        this.content = content;
    }

    public void updateWithDto(MissionUpdateRequest dto) {

    }
}
