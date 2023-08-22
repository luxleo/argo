package com.project.argo.response.team.study;

import com.project.argo.domain.team.study.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class StudyDetailResponse {
    private long studyId;
    private List<Course> courses;
    private List<Mission>
}
