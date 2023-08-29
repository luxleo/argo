package com.project.argo.service.team.project;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.project.Project;
import com.project.argo.domain.team.project.ProjectInfo;
import com.project.argo.domain.team.project.recruit.Position;
import com.project.argo.domain.team.project.recruit.ProjectGroup;
import com.project.argo.repository.team.project.ProjectRepository;
import com.project.argo.repository.team.project.recruit.PositionRepository;
import com.project.argo.request.team.project.ProjectCreateRequest;
import com.project.argo.request.team.project.ProjectGroupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 전반적으로 presentation layer에 출력하도록 할 거 같다... + 기본적인 crud도
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final PositionRepository positionRepository;
    private final ProjectRepository projectRepository;

    public long createProjectTeam(ProjectCreateRequest dto, Member leader) {
        ProjectInfo projectInfo = new ProjectInfo(dto.getDesc());
        Project project = Project.builder()
                .name(dto.getProjectName())
                .leader(leader)
                .projectInfo(projectInfo)
                .build();
        project.allocateInfo(projectInfo);
        List<ProjectGroupDto> projectGroupsData = dto.getProjectGroups();

        for (ProjectGroupDto projectGroupsDatum : projectGroupsData) {
            Position findPosition = positionRepository.findByName(projectGroupsDatum.getPositionName());
            ProjectGroup newGroup = ProjectGroup.builder()
                    .position(findPosition)
                    .maxTO(projectGroupsDatum.getMaxTo())
                    .build();
            project.addGroup(newGroup);
        }
        projectRepository.save(project);
        return project.getId();
    }
}
