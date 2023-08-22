package com.project.argo.repository.article;

import com.project.argo.domain.article.comment.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.argo.domain.QMember.member;
import static com.project.argo.domain.article.comment.QComment.comment;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ArticleQueryRepository {
    private final JPAQueryFactory query;
    //TODO: 페이징 적용하고 dto로 반환한다.
    public List<Comment> findFullArticleById(Long articleId) {
        return query.select(comment).distinct()
                .from(comment)
                .join(comment.author, member)
                .fetchJoin()
                .where(comment.article.id.eq(articleId))
                .fetch();
    }
}
