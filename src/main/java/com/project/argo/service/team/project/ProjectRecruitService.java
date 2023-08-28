package com.project.argo.service.team.project;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.project.Project;
import com.project.argo.domain.team.project.ProjectInfo;
import com.project.argo.domain.team.project.recruit.Position;
import com.project.argo.domain.team.project.recruit.ProjectGroup;
import com.project.argo.domain.team.project.recruit.Role;
import com.project.argo.repository.team.project.ProjectRepository;
import com.project.argo.repository.team.project.recruit.PositionRepository;
import com.project.argo.repository.team.project.recruit.ProjectGroupRepository;
import com.project.argo.repository.team.project.recruit.ProjectInfoRepository;
import com.project.argo.repository.team.project.recruit.RoleRepository;
import com.project.argo.request.team.project.ProjectCreateRequest;
import com.project.argo.request.team.project.ProjectGroupDto;
import com.project.argo.request.team.project.ProjectUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectRecruitService {
    private final ProjectRepository projectRepository;
    private final ProjectGroupRepository projectGroupRepository;
    private final ProjectInfoRepository projectInfoRepository;
    private final PositionRepository positionRepository;
    private final RoleRepository roleRepository;

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

    public void joinUser(Member user, ProjectUpdateDto dto) {
        Role role = new Role(user);
        ProjectGroup findGroup = projectGroupRepository.findById(dto.getTargetGroupId()).orElseThrow();

        findGroup.addRoles(role);
    }

    public void disjoinUser(Long grounId,Long roleId) {
        ProjectGroup findGroup = projectGroupRepository.findById(grounId).orElseThrow();
        Role foundRole = roleRepository.findById(roleId).orElseThrow();

        findGroup.removeRole(foundRole);
    }
}
