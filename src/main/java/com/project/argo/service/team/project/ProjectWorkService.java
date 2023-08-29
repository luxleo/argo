package com.project.argo.service.team.project;

import com.project.argo.domain.team.project.Project;
import com.project.argo.domain.team.project.recruit.Role;
import com.project.argo.domain.team.project.work.Job;
import com.project.argo.domain.team.project.work.Stage;
import com.project.argo.domain.team.project.work.Task;
import com.project.argo.domain.team.project.work.WorkStatus;
import com.project.argo.repository.team.project.ProjectRepository;
import com.project.argo.repository.team.project.recruit.RoleRepository;
import com.project.argo.repository.team.project.work.JobRepository;
import com.project.argo.repository.team.project.work.StageRepository;
import com.project.argo.repository.team.project.work.TaskRepository;
import com.project.argo.request.team.project.work.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectWorkService {
    private final ProjectRepository projectRepository;
    private final StageRepository stageRepository;
    private final JobRepository jobRepository;
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository;
    //LEARN: @OneToMany에서 cascade=ALL로 지정해주면 부모 층에서 직접 관리해줄 수 있다.
    //TODO: dto로 반환하도록 해야한다.
    public Stage createStage(StageCreateRequest dto) {
        Project findProject = projectRepository.findById(dto.getProjectId()).orElseThrow();
        Stage newStage = Stage.builder()
                .name(dto.getStageName())
                .build();
        findProject.addStage(newStage);
        return newStage;
    }
    public Job createJob(JobCreateRequest dto) {
        Stage findStage = stageRepository.findById(dto.getStageId()).orElseThrow();
        Role worker = roleRepository.findById(dto.getMemberId()).orElseThrow();
        Job newJob = Job.builder()
                .name(dto.getJobName())
                .worker(worker)
                .build();
        findStage.addJob(newJob);
        return newJob;
    }
    public Task createTask(TaskCreateRequest dto) {
        Job findJob = jobRepository.findById(dto.getJobId()).orElseThrow();
        Task newTask = Task.builder()
                .name(dto.getName())
                .desc(dto.getDesc())
                .build();
        findJob.addTask(newTask);
        return newTask;
    }

    /**
     * dirty checking으로 update 실행한다.
     */
    public Task updateTask(TaskUpdateRequest dto) {
        Task findTask = taskRepository.findById(dto.getId()).orElseThrow();
        Task.TaskBuilder taskBuilder = findTask.toBuilder();
        Task updatedTask = taskBuilder.
                name(dto.getName())
                .desc(dto.getDesc())
                .build();
        updatedTask.setStatus(WorkStatus.valueOf(dto.getStatus()));

        findTask.updateTask(updatedTask);
        return findTask;
    }

    public Job updateJob(JobUpdateRequest dto) {
        Job findJob = jobRepository.findById(dto.getId()).orElseThrow();

        Job toUpdate = Job.builder()
                .name(dto.getName())
                .build();
        toUpdate.setStatus(WorkStatus.valueOf(dto.getStatus()));

        findJob.updateJob(toUpdate);
        return findJob;
    }

    public Stage updateStage(StageUpdateRequest dto) {
        Stage findStage = stageRepository.findById(dto.getId()).orElseThrow();

        Stage toUpdate = Stage.builder()
                .name(dto.getName())
                .build();
        toUpdate.setStatus(WorkStatus.valueOf(dto.getStatus()));

        findStage.updateStage(toUpdate);
        return findStage;
    }
}