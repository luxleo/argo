package com.project.argo.domain.team.project.recruit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class PositionCategory {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "position_category_id")
    private Integer id;
    @Column(name = "position_category_name")
    private String name;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "category")
    private List<Position> positions = new ArrayList<>();
}
