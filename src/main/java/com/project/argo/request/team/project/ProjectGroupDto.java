package com.project.argo.request.team.project;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProjectGroupDto {
    private String positionName;
    private Integer maxTo;
    @Builder
    public ProjectGroupDto(String positionName, Integer maxTo) {
        this.positionName = positionName;
        this.maxTo = maxTo;
    }
}
