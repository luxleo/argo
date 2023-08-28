package com.project.argo.repository.team.project.recruit;

import com.project.argo.domain.team.project.recruit.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Long> {
    Position findByName(String name);
}
