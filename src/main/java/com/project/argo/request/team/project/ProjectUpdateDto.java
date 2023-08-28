package com.project.argo.request.team.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProjectUpdateDto {
    private Long projectId;
    private Long targetGroupId;

    public ProjectUpdateDto(Long projectId, Long targetGroupId) {
        this.projectId = projectId;
        this.targetGroupId = targetGroupId;
    }
}
