package com.project.argo.repository.team;

import com.project.argo.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
    Team findByName(String name);
}
