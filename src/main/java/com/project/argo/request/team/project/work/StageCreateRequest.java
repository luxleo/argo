package com.project.argo.request.team.project.work;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class StageCreateRequest {
    private Long projectId;
    private String stageName;
}
