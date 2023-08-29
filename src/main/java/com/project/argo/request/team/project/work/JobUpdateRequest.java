package com.project.argo.request.team.project.work;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class JobUpdateRequest {
    private Long id;
    private String name;
    private String status;
}
