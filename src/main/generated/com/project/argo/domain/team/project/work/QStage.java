package com.project.argo.domain.team.project.work;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStage is a Querydsl query type for Stage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStage extends EntityPathBase<Stage> {

    private static final long serialVersionUID = 364284464L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStage stage = new QStage("stage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Job, QJob> jobs = this.<Job, QJob>createList("jobs", Job.class, QJob.class, PathInits.DIRECT2);

    public final com.project.argo.domain.team.project.QProject project;

    public final EnumPath<WorkStatus> stageStatus = createEnum("stageStatus", WorkStatus.class);

    public QStage(String variable) {
        this(Stage.class, forVariable(variable), INITS);
    }

    public QStage(Path<? extends Stage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStage(PathMetadata metadata, PathInits inits) {
        this(Stage.class, metadata, inits);
    }

    public QStage(Class<? extends Stage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new com.project.argo.domain.team.project.QProject(forProperty("project"), inits.get("project")) : null;
    }

}

