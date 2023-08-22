package com.project.argo.domain.article.comment;

import com.project.argo.domain.Member;
import com.project.argo.domain.article.Article;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
@NoArgsConstructor
public abstract class Comment {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "comment_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
    private String content;

    public void commentToArticle(Article article) {
        this.article = article;
        article.getComments().add(this);
    }
}
