package com.project.argo.domain.team.study;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = -337569782L;

    public static final QStudy study = new QStudy("study");

    public final com.project.argo.domain.team.QTeam _super = new com.project.argo.domain.team.QTeam(this);

    public final ListPath<Course, QCourse> courses = this.<Course, QCourse>createList("courses", Course.class, QCourse.class, PathInits.DIRECT2);

    public final StringPath desc = createString("desc");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final ListPath<com.project.argo.domain.team.MapMemberTeam, com.project.argo.domain.team.QMapMemberTeam> members = _super.members;

    //inherited
    public final StringPath name = _super.name;

    public final EnumPath<StudyStatus> studyStatus = createEnum("studyStatus", StudyStatus.class);

    public final StringPath title = createString("title");

    public QStudy(String variable) {
        super(Study.class, forVariable(variable));
    }

    public QStudy(Path<? extends Study> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudy(PathMetadata metadata) {
        super(Study.class, metadata);
    }

}

