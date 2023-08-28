package com.project.argo.service.team.project;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.project.Project;
import com.project.argo.domain.team.project.recruit.ProjectGroup;
import com.project.argo.domain.team.project.recruit.Role;
import com.project.argo.repository.team.project.ProjectRepository;
import com.project.argo.repository.team.project.recruit.ProjectGroupRepository;
import com.project.argo.request.team.project.ProjectCreateRequest;
import com.project.argo.request.team.project.ProjectGroupDto;
import com.project.argo.request.team.project.ProjectUpdateDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProjectRecruitServiceTest {
    static final String ORIGINAL_PROJECT = "origin";
    @Autowired
    EntityManager em;
    @Autowired
    ProjectRecruitService projectRecruitService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectGroupRepository groupRepository;
    @BeforeEach
    void before() {
        Member member1 = Member.builder()
                .name("test")
                .email("test@gmail.com")
                .password("1234")
                .build();
        em.persist(member1);

        List<ProjectGroupDto> groupData = new ArrayList<>();
        groupData.add(new ProjectGroupDto("안드로이드", 3));
        groupData.add(new ProjectGroupDto("웹서버", 2));

        ProjectCreateRequest dto = ProjectCreateRequest.builder()
                .desc("test project")
                .projectName(ORIGINAL_PROJECT)
                .projectGroups(groupData)
                .build();
        long createdId = projectRecruitService.createProjectTeam(dto, member1);
    }

    @Test
    @DisplayName("프로젝트 생성시 모집 그룹까지 지정하여 생성할 수 있다.")
    void createNewProject() {
        //given
        Member member1 = Member.builder()
                .name("test1")
                .email("test@gmail.com")
                .password("1234")
                .build();
        em.persist(member1);

        List<ProjectGroupDto> groupData = new ArrayList<>();
        groupData.add(new ProjectGroupDto("안드로이드", 3));
        groupData.add(new ProjectGroupDto("웹서버", 2));

        ProjectCreateRequest dto = ProjectCreateRequest.builder()
                .desc("test project")
                .projectName("test project")
                .projectGroups(groupData)
                .build();
        //when
        long createdId = projectRecruitService.createProjectTeam(dto, member1);
        //then
        Project findProject = em.createQuery(
                        "select p from Project p " +
                                "join fetch p.groups g " +
                                "where p.id = :id", Project.class
                ).setParameter("id", createdId)
                .getResultList().stream().findAny().orElseThrow();

        assertThat(findProject.getName()).isEqualTo("test project");
        assertThat(findProject.getGroups().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("멤버는 프로젝트에 Role로 참여,탈퇴 할 수있다.")
    @Commit
    void joinAndDisjoin() {
        //given
        Member m1 = Member.builder()
                .name("test1")
                .build();
        Member m2 = Member.builder()
                .name("test1")
                .build();
        em.persist(m1);
        em.persist(m2);
        //when 임의의 유저 두명을 하나의 그룹에 조인 시킨다.
        Project findProject = projectRepository.findByName(ORIGINAL_PROJECT).orElseThrow();
        ProjectGroup findGroup = findProject.getGroups().stream().findAny().orElseThrow();

        ProjectUpdateDto dto = new ProjectUpdateDto();
        dto.setTargetGroupId(findGroup.getId());

        projectRecruitService.joinUser(m1, dto);
        projectRecruitService.joinUser(m2, dto);
        //then
        List<Role> newRoles = findGroup.getRoles();
        int iniRoleSize = newRoles.size();
        assertThat(newRoles.size()).isEqualTo(iniRoleSize);
        em.flush();
        em.clear();
        //and then
        for (Role newRole : newRoles) {
            projectRecruitService.disjoinUser(findGroup.getId(), newRole.getId());
            ProjectGroup updatedGroup = groupRepository.findById(findGroup.getId()).orElseThrow();
            List<Role> updatedRoles = updatedGroup.getRoles();
            assertThat(updatedRoles.size()).isEqualTo(--iniRoleSize);
        }
    }
}