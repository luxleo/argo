package com.project.argo.domain.team.project.recruit;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Position {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "position_id")
    private Long id;
    @Column(name = "position_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_category_id")
    private PositionCategory category;

    public void allocateCategory(PositionCategory category) {
        this.category = category;
    }
    @Builder
    public Position(String name) {
        this.name = name;
    }
}
