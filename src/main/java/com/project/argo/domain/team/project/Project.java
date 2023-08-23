package com.project.argo.domain.team.project;

import com.project.argo.domain.team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("project") //TODO: 아마 멤버별 포지션이 있을 것이다.
public class Project extends Team {
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stage> stages = new ArrayList<>();
    /*
    TODO:
    private List<Position> positions;
     */
    @OneToMany(mappedBy ="project", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Position> positions = new ArrayList<>();
}
