package com.project.argo.service.team.project;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.project.Project;
import com.project.argo.domain.team.project.recruit.ProjectGroup;
import com.project.argo.domain.team.project.recruit.Role;
import com.project.argo.domain.team.project.work.Job;
import com.project.argo.domain.team.project.work.Stage;
import com.project.argo.domain.team.project.work.Task;
import com.project.argo.domain.team.project.work.WorkStatus;
import com.project.argo.repository.team.project.ProjectRepository;
import com.project.argo.repository.team.project.work.StageRepository;
import com.project.argo.repository.team.project.work.TaskRepository;
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
    static final String ORIGINAL_PROJECT = "origin1";
    @Autowired
    EntityManager em;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectWorkService projectWorkService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    StageRepository stageRepository;
    @Autowired
    TaskRepository taskRepository;

    //FIXME: @SpringBootTest 붙여서 하면 롤백이 되지 않아서 데이터 중복이 일어나더라=> 어떻게 해결할지는 알아보자.
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
        long createdId = projectService.createProjectTeam(dto, member1);
        Project newProject = projectRepository.findById(createdId).orElseThrow();
        Role newRole = new Role(member1);
        Stage newStage = Stage.builder()
                .name("origin stage")
                .build();
        Job newJob = Job.builder()
                .name("origin job")
                .worker(newRole)
                .build();
        Task newTask = Task.builder()
                .name("origin task")
                .desc("origin task desc")
                .build();
        ProjectGroup findProjectGroup = newProject.getGroups().stream().findAny().orElseThrow();

        findProjectGroup.addRoles(newRole);
        newJob.addTask(newTask);
        newJob.allocateWorker(newRole);
        newStage.addJob(newJob);
        newProject.addStage(newStage);
        em.persist(newStage);
        em.persist(newJob);
        em.persist(newTask);
        em.persist(newRole);

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
    @Test
    @DisplayName("task 업데이트시 dto로 하지 않고 Task그대로 넘겨 주는데 추가가 되지 않고 업데이트만 된다.")
    void updateTask() {
        //given
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks.size()).isEqualTo(1);
        Task findTask = tasks.get(0);
        //when
        Task updateTask = Task.builder()
                .name("update Task")
                .desc("update Task desc")
                .build();
        updateTask.setStatus(WorkStatus.FINISHED);
        findTask.updateTask(updateTask);
        em.flush();
        em.clear();
        //then
        List<Task> tasks1 = taskRepository.findAll();
        assertThat(tasks1.size()).isEqualTo(1);
        Task updatedTask = tasks1.get(0);

        assertThat(updatedTask.getName()).isEqualTo("update Task");
        assertThat(updatedTask.getStatus()).isEqualTo(WorkStatus.FINISHED);
    }
}