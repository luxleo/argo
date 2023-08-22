package com.project.argo.service.article;

import com.project.argo.domain.Member;
import com.project.argo.domain.article.Article;
import com.project.argo.domain.article.Question;
import com.project.argo.repository.MemberRepository;
import com.project.argo.repository.article.ArticleQueryRepository;
import com.project.argo.repository.article.ArticleRepository;
import com.project.argo.request.article.QuestionCreateDto;
import com.project.argo.request.article.QuestionUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long writeQuestion(QuestionCreateDto dto) {
        Member findMember = memberRepository.findById(dto.getMemberId()).orElseThrow();
        Question newQ = Question.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .member(findMember)
                .build();

        articleRepository.save(newQ);
        return newQ.getId();
    }

    @Transactional
    public Long updateQuestion(QuestionUpdateDto dto) {
        Article findArticle = articleRepository.findById(dto.getId()).orElseThrow();
        findArticle.setTitle(dto.getTitle());
        findArticle.setContent(dto.getContent());
        return findArticle.getId();
    }
}
