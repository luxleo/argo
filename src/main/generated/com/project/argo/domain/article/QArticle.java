package com.project.argo.domain.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = 1896583207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticle article = new QArticle("article");

    public final com.project.argo.domain.QMember author;

    public final ListPath<com.project.argo.domain.article.comment.Comment, com.project.argo.domain.article.comment.QComment> comments = this.<com.project.argo.domain.article.comment.Comment, com.project.argo.domain.article.comment.QComment>createList("comments", com.project.argo.domain.article.comment.Comment.class, com.project.argo.domain.article.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QArticle(String variable) {
        this(Article.class, forVariable(variable), INITS);
    }

    public QArticle(Path<? extends Article> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArticle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArticle(PathMetadata metadata, PathInits inits) {
        this(Article.class, metadata, inits);
    }

    public QArticle(Class<? extends Article> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.project.argo.domain.QMember(forProperty("author")) : null;
    }

}

