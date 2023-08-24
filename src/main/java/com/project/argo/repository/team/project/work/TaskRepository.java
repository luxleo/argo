package com.project.argo.repository.team.project.work;

import com.project.argo.domain.team.project.work.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
