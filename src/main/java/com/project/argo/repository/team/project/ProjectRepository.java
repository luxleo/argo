package com.project.argo.repository.team.project;

import com.project.argo.domain.team.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
