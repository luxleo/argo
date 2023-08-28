package com.project.argo.domain.team.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectInfo is a Querydsl query type for ProjectInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectInfo extends EntityPathBase<ProjectInfo> {

    private static final long serialVersionUID = 590008504L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectInfo projectInfo = new QProjectInfo("projectInfo");

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProject project;

    public QProjectInfo(String variable) {
        this(ProjectInfo.class, forVariable(variable), INITS);
    }

    public QProjectInfo(Path<? extends ProjectInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectInfo(PathMetadata metadata, PathInits inits) {
        this(ProjectInfo.class, metadata, inits);
    }

    public QProjectInfo(Class<? extends ProjectInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
    }

}

