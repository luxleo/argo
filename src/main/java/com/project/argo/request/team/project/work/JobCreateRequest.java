package com.project.argo.request.team.project.work;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class JobCreateRequest {
    private String jobName;
    private Long stageId;
    private Long memberId;
}
