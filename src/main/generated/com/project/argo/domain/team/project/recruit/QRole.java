package com.project.argo.domain.team.project.recruit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = -1597279755L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRole role = new QRole("role");

    public final QProjectGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.project.argo.domain.team.project.work.Job, com.project.argo.domain.team.project.work.QJob> jobs = this.<com.project.argo.domain.team.project.work.Job, com.project.argo.domain.team.project.work.QJob>createList("jobs", com.project.argo.domain.team.project.work.Job.class, com.project.argo.domain.team.project.work.QJob.class, PathInits.DIRECT2);

    public final com.project.argo.domain.QMember member;

    public QRole(String variable) {
        this(Role.class, forVariable(variable), INITS);
    }

    public QRole(Path<? extends Role> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRole(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRole(PathMetadata metadata, PathInits inits) {
        this(Role.class, metadata, inits);
    }

    public QRole(Class<? extends Role> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QProjectGroup(forProperty("group"), inits.get("group")) : null;
        this.member = inits.isInitialized("member") ? new com.project.argo.domain.QMember(forProperty("member")) : null;
    }

}

