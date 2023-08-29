package com.project.argo.service.team.project;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.project.Project;
import com.project.argo.domain.team.project.work.Stage;
import com.project.argo.repository.team.project.ProjectRepository;
import com.project.argo.repository.team.project.work.StageRepository;
import com.project.argo.request.team.project.ProjectCreateRequest;
import com.project.argo.request.team.project.ProjectGroupDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProjectWorkServiceTest {
    static final String ORIGINAL_PROJECT = "origin";
    @Autowired
    EntityManager em;
    @Autowired
    ProjectRecruitService projectRecruitService;
    @Autowired
    ProjectWorkService projectWorkService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    StageRepository stageRepository;

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
        Stage newStage = Stage.builder()
                .name("origin stage")
                .build();
        Project findProject = projectRepository.findByName(ORIGINAL_PROJECT).orElseThrow();
        findProject.addStage(newStage);
    }

    @Test
    @DisplayName("프로젝트 내에서 작업의 최상위 단위는 stage이다. agile을 준수하자")
    void createStage() {
        //given
        Project findProject = projectRepository.findByName(ORIGINAL_PROJECT).orElseThrow();
        Stage newStage = Stage.builder()
                .name("test stage")
                .build();
        //when
        findProject.addStage(newStage);
        em.flush();
        em.clear();
        //then
        List<Stage> allStages = stageRepository.findAll();
        assertThat(allStages.size()).isEqualTo(2);
    }
}