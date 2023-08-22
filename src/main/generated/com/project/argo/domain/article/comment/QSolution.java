package com.project.argo.domain.article.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSolution is a Querydsl query type for Solution
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSolution extends EntityPathBase<Solution> {

    private static final long serialVersionUID = 293433367L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSolution solution = new QSolution("solution");

    public final QComment _super;

    // inherited
    public final com.project.argo.domain.article.QArticle article;

    // inherited
    public final com.project.argo.domain.QMember author;

    //inherited
    public final StringPath content;

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<Integer> voteCnt = createNumber("voteCnt", Integer.class);

    public QSolution(String variable) {
        this(Solution.class, forVariable(variable), INITS);
    }

    public QSolution(Path<? extends Solution> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSolution(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSolution(PathMetadata metadata, PathInits inits) {
        this(Solution.class, metadata, inits);
    }

    public QSolution(Class<? extends Solution> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QComment(type, metadata, inits);
        this.article = _super.article;
        this.author = _super.author;
        this.content = _super.content;
        this.id = _super.id;
    }

}

