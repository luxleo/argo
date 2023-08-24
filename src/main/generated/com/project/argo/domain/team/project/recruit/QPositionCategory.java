package com.project.argo.domain.team.project.recruit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPositionCategory is a Querydsl query type for PositionCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPositionCategory extends EntityPathBase<PositionCategory> {

    private static final long serialVersionUID = 1005661254L;

    public static final QPositionCategory positionCategory = new QPositionCategory("positionCategory");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final ListPath<Position, QPosition> positions = this.<Position, QPosition>createList("positions", Position.class, QPosition.class, PathInits.DIRECT2);

    public QPositionCategory(String variable) {
        super(PositionCategory.class, forVariable(variable));
    }

    public QPositionCategory(Path<? extends PositionCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPositionCategory(PathMetadata metadata) {
        super(PositionCategory.class, metadata);
    }

}

