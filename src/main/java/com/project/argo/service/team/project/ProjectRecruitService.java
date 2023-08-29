package com.project.argo.service.team.project;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.project.recruit.ProjectGroup;
import com.project.argo.domain.team.project.recruit.Role;
import com.project.argo.repository.team.project.recruit.ProjectGroupRepository;
import com.project.argo.repository.team.project.recruit.RoleRepository;
import com.project.argo.request.team.project.ProjectUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectRecruitService {
    private final RoleRepository roleRepository;
    private final ProjectGroupRepository projectGroupRepository;


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
