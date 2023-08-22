package com.project.argo.domain.team;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMapMemberTeam is a Querydsl query type for MapMemberTeam
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMapMemberTeam extends EntityPathBase<MapMemberTeam> {

    private static final long serialVersionUID = -1643334727L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMapMemberTeam mapMemberTeam = new QMapMemberTeam("mapMemberTeam");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> joinedDate = createDate("joinedDate", java.time.LocalDate.class);

    public final com.project.argo.domain.QMember member;

    public final QTeam team;

    public QMapMemberTeam(String variable) {
        this(MapMemberTeam.class, forVariable(variable), INITS);
    }

    public QMapMemberTeam(Path<? extends MapMemberTeam> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMapMemberTeam(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMapMemberTeam(PathMetadata metadata, PathInits inits) {
        this(MapMemberTeam.class, metadata, inits);
    }

    public QMapMemberTeam(Class<? extends MapMemberTeam> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.project.argo.domain.QMember(forProperty("member")) : null;
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

