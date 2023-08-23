package com.project.argo.response.team.study;

import com.project.argo.domain.team.study.Course;
import com.project.argo.domain.team.study.Mission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class CourseDetailDto {
    private Long id;
    private String name;
    private String desc;
    private List<Mission> missions;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public CourseDetailDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.desc = course.getDesc();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
    }

    public void addMissions(List<Mission> missions) {
        this.missions = missions;
    }
}
