package com.project.argo.request.team.study;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CourseUpdateRequest {
    private Long courseId;
    private String name;
    private String desc;
}
