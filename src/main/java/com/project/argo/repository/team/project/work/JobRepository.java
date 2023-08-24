package com.project.argo.repository.team.project.work;

import com.project.argo.domain.team.project.work.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
