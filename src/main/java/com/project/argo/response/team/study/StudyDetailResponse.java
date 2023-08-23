package com.project.argo.response.team.study;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class StudyDetailResponse {
    private long studyId;
    private List<CourseDetailDto> courses;
    @Builder
    public StudyDetailResponse(long studyId, List<CourseDetailDto> courses) {
        this.studyId = studyId;
        this.courses = courses;
    }
}
