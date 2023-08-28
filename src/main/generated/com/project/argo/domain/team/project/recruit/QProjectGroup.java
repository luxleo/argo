package com.project.argo.domain.team.project.recruit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectGroup is a Querydsl query type for ProjectGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectGroup extends EntityPathBase<ProjectGroup> {

    private static final long serialVersionUID = 1823851877L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectGroup projectGroup = new QProjectGroup("projectGroup");

    public final NumberPath<Integer> currentUserNum = createNumber("currentUserNum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxTO = createNumber("maxTO", Integer.class);

    public final QPosition position;

    public final com.project.argo.domain.team.project.QProject project;

    public final EnumPath<RecruitStatus> recruitStatus = createEnum("recruitStatus", RecruitStatus.class);

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public QProjectGroup(String variable) {
        this(ProjectGroup.class, forVariable(variable), INITS);
    }

    public QProjectGroup(Path<? extends ProjectGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectGroup(PathMetadata metadata, PathInits inits) {
        this(ProjectGroup.class, metadata, inits);
    }

    public QProjectGroup(Class<? extends ProjectGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.position = inits.isInitialized("position") ? new QPosition(forProperty("position"), inits.get("position")) : null;
        this.project = inits.isInitialized("project") ? new com.project.argo.domain.team.project.QProject(forProperty("project"), inits.get("project")) : null;
    }

}

