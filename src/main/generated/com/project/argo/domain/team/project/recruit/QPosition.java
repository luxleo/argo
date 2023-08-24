package com.project.argo.domain.team.project.recruit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPosition is a Querydsl query type for Position
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPosition extends EntityPathBase<Position> {

    private static final long serialVersionUID = -976899288L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPosition position = new QPosition("position1");

    public final QPositionCategory category;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.project.argo.domain.team.project.work.Job, com.project.argo.domain.team.project.work.QJob> jobs = this.<com.project.argo.domain.team.project.work.Job, com.project.argo.domain.team.project.work.QJob>createList("jobs", com.project.argo.domain.team.project.work.Job.class, com.project.argo.domain.team.project.work.QJob.class, PathInits.DIRECT2);

    public final com.project.argo.domain.QMember member;

    public final StringPath name = createString("name");

    public final com.project.argo.domain.team.project.QProject project;

    public QPosition(String variable) {
        this(Position.class, forVariable(variable), INITS);
    }

    public QPosition(Path<? extends Position> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPosition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPosition(PathMetadata metadata, PathInits inits) {
        this(Position.class, metadata, inits);
    }

    public QPosition(Class<? extends Position> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QPositionCategory(forProperty("category")) : null;
        this.member = inits.isInitialized("member") ? new com.project.argo.domain.QMember(forProperty("member")) : null;
        this.project = inits.isInitialized("project") ? new com.project.argo.domain.team.project.QProject(forProperty("project"), inits.get("project")) : null;
    }

}

