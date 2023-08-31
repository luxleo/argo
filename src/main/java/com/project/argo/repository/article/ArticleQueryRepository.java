package com.project.argo.repository.article;

import com.project.argo.domain.article.Article;
import com.project.argo.repository.article.cond.QuestionSearchCond;
import com.project.argo.response.article.QuestionListResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.argo.domain.QMember.member;
import static com.project.argo.domain.article.QArticle.article;
import static com.project.argo.domain.article.QQuestion.question;
import static com.project.argo.domain.article.comment.QComment.comment;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ArticleQueryRepository {
    private final JPAQueryFactory query;

    public Article findFullArticleById(Long id) {
        return query.selectFrom(article)
                .join(article.author, member).fetchJoin()
                .leftJoin(article.comments, comment).fetchJoin()
                .where(article.id.eq(id))
                .fetchOne();
    }

    //FIXME: cond 별로 조회가능 하도록
    public Page<QuestionListResponse> getQuestions(QuestionSearchCond cond, Pageable pageable) {
        List<QuestionListResponse> content = query.select(Projections.bean(QuestionListResponse.class, question.id, question.title,question.author))
                .from(question)
                .join(question.author, member).fetchJoin()
                .where(titleContainsIgnoreCase(cond.getTitle()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        //LEARN: projection절의 조회할 필드도 최소한 으로 하자.
        JPAQuery<Long> preCnt = query.select(question.id.count())
                .from(question)
                .where(titleContainsIgnoreCase(cond.getTitle()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        //LEARN: fetchCount가 deprecated 되었다. 아래와 같이 작성하자
        return PageableExecutionUtils.getPage(content, pageable, ()->preCnt.fetch().size());
    }

    private BooleanExpression titleContainsIgnoreCase(String title) {
        return StringUtils.isNullOrEmpty(title) ? null : question.title.containsIgnoreCase(title);
    }
}
