package com.project.argo.request.team.project.work;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class TaskUpdateRequest {
    private Long id;
    private String name;
    private String desc;
    private String status;
}
