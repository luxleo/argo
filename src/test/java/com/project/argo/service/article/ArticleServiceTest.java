package com.project.argo.service.article;

import com.project.argo.domain.Member;
import com.project.argo.domain.article.Article;
import com.project.argo.domain.article.Question;
import com.project.argo.domain.article.comment.Comment;
import com.project.argo.repository.MemberRepository;
import com.project.argo.repository.article.ArticleQueryRepository;
import com.project.argo.repository.article.ArticleRepository;
import com.project.argo.repository.article.comment.CommentRepository;
import com.project.argo.request.article.QuestionCreateDto;
import com.project.argo.request.article.QuestionUpdateDto;
import com.project.argo.request.article.comment.AnswerCreateDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ArticleServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleQueryRepository articleQueryRepository;
    @Autowired
    MemberRepository memberRepository;


    @BeforeEach
    void before() {
        Member m1 = Member.builder()
                .name("test1")
                .email("test1@gmail.com")
                .password("1234")
                .build();
        Member m2 = Member.builder()
                .name("test2")
                .email("test2@gmail.com")
                .password("1234")
                .build();
        Member m3 = Member.builder()
                .name("test3")
                .email("test3@gmail.com")
                .password("1234")
                .build();
        em.persist(m1);
        em.persist(m2);
        em.persist(m3);

        Article a1 = Question.builder()
                .title("test article")
                .content("test content")
                .member(m1)
                .build();
        em.persist(a1);
    }

    @Test
    @DisplayName("article service로 정상 만들기")
    void article_create() {
        String testTitle = "dragon title";
        String testContet = "dragon content";
        Member findMember = memberRepository.findAll().stream().findAny().orElseThrow();
        long testMemberId = findMember.getId();
        QuestionCreateDto dto = QuestionCreateDto.builder()
                .title(testTitle)
                .content(testContet)
                .memberId(testMemberId)
                .build();

        Long createdId = articleService.writeQuestion(dto);
        em.flush();
        em.clear();
        Article findArticle = articleRepository.findById(createdId).orElseThrow();

        assertThat(findArticle.getTitle())
                .isEqualTo(testTitle);
        assertThat(findArticle.getContent()).isEqualTo(testContet);

        Member findMember2 = memberRepository.findById(testMemberId).orElseThrow();
        assertThat(findArticle.getAuthor().getName()).isEqualTo(findMember2.getName());
    }

    @Test
    @DisplayName("article update")
    void article_update() {
        Article findArticle = articleRepository.findAll().stream().findAny().orElseThrow();
        String updateTitle = "update title";
        String updateContent = "update content";

        QuestionUpdateDto dto = new QuestionUpdateDto(findArticle.getId(), updateTitle, updateContent);
        Long updatedArticleId = articleService.updateQuestion(dto);
        em.flush();
        em.clear();

        Article updatedArticle = articleRepository.findById(updatedArticleId).orElseThrow();
        assertThat(updatedArticle.getTitle()).isEqualTo(updateTitle);
        assertThat(updatedArticle.getContent()).isEqualTo(updateContent);
    }

    @Test
    @DisplayName("질문에 답글을 달 수있다.")
    @Commit
    void add_answer_to_question() {
        //given
        Article findArticle = articleRepository.findAll().stream().findAny().orElseThrow();
        Member findMember = memberRepository.findAll().stream().findAny().orElseThrow();
        String answerContent = "test answer";

        AnswerCreateDto dto = AnswerCreateDto.builder()
                .articleId(findArticle.getId())
                .authorId(findMember.getId())
                .content(answerContent)
                .build();
        long newAnswerId = commentService.addAnswerToQuestion(dto);

        em.flush();
        em.clear();

        //when
        Comment findAnswer = commentRepository.findById(newAnswerId).orElseThrow();
        //then
        assertThat(findAnswer.getArticle().getId()).isEqualTo(findArticle.getId());

        assertThat(findAnswer.getAuthor().getName()).isEqualTo(findMember.getName());
        assertThat(findAnswer.getContent()).isEqualTo(answerContent);
        List<Comment> findComments = articleQueryRepository.findFullArticleById(findArticle.getId());
        assertThat(findComments.size()).isEqualTo(1);
    }
}