package com.project.argo.request.team.project;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class ProjectCreateRequest {
    private String projectName;
    private String desc; // for project info
    private List<ProjectGroupDto> projectGroups;

    @Builder
    public ProjectCreateRequest(String projectName, String desc, List<ProjectGroupDto> projectGroups) {
        this.projectName = projectName;
        this.desc = desc;
        this.projectGroups = projectGroups;
    }
}
