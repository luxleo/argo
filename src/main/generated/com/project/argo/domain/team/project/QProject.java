package com.project.argo.domain.team.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = 1153727722L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProject project = new QProject("project");

    public final com.project.argo.domain.team.QTeam _super = new com.project.argo.domain.team.QTeam(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final com.project.argo.domain.QMember leader;

    //inherited
    public final ListPath<com.project.argo.domain.team.MapMemberTeam, com.project.argo.domain.team.QMapMemberTeam> members = _super.members;

    //inherited
    public final StringPath name = _super.name;

    public final ListPath<com.project.argo.domain.team.project.work.Stage, com.project.argo.domain.team.project.work.QStage> stages = this.<com.project.argo.domain.team.project.work.Stage, com.project.argo.domain.team.project.work.QStage>createList("stages", com.project.argo.domain.team.project.work.Stage.class, com.project.argo.domain.team.project.work.QStage.class, PathInits.DIRECT2);

    public QProject(String variable) {
        this(Project.class, forVariable(variable), INITS);
    }

    public QProject(Path<? extends Project> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProject(PathMetadata metadata, PathInits inits) {
        this(Project.class, metadata, inits);
    }

    public QProject(Class<? extends Project> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.leader = inits.isInitialized("leader") ? new com.project.argo.domain.QMember(forProperty("leader")) : null;
    }

}

