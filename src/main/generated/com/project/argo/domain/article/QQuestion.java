package com.project.argo.domain.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -1272455467L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final QArticle _super;

    // inherited
    public final com.project.argo.domain.QMember author;

    //inherited
    public final ListPath<com.project.argo.domain.article.comment.Comment, com.project.argo.domain.article.comment.QComment> comments;

    //inherited
    public final StringPath content;

    //inherited
    public final NumberPath<Long> id;

    public final EnumPath<QuestionStatus> status = createEnum("status", QuestionStatus.class);

    //inherited
    public final StringPath title;

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QArticle(type, metadata, inits);
        this.author = _super.author;
        this.comments = _super.comments;
        this.content = _super.content;
        this.id = _super.id;
        this.title = _super.title;
    }

}

