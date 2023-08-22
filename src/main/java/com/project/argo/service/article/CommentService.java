package com.project.argo.service.article;

import com.project.argo.domain.Member;
import com.project.argo.domain.article.Article;
import com.project.argo.domain.article.comment.Solution;
import com.project.argo.repository.MemberRepository;
import com.project.argo.repository.article.ArticleRepository;
import com.project.argo.repository.article.comment.CommentRepository;
import com.project.argo.request.article.comment.AnswerCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long addAnswerToQuestion(AnswerCreateDto dto) {
        Member author = memberRepository.findById(dto.getAuthorId()).orElseThrow();
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow();

        Solution newComment = Solution.builder()
                .author(author)
                .article(article)
                .content(dto.getContent())
                .build();
        commentRepository.save(newComment);
        return newComment.getId();
    }
}
