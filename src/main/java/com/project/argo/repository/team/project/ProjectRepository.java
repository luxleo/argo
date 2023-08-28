package com.project.argo.repository.team.project;

import com.project.argo.domain.team.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query("select p from Project p join fetch p.groups g where p.id = :id and g.id = :groupId")
    Optional<Project> findByIdWithGroups(@Param("id") Long id, @Param("groupId") Long groupId);

    Optional<Project> findByName(String name);
}
