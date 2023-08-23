package com.project.argo.request.team.study;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MissionUpdateRequest {
    private Long id;
    private String content;
    private int nth;
}
