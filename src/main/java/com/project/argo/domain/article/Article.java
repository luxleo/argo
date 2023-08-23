package com.project.argo.domain.article;

import com.project.argo.domain.Member;
import com.project.argo.domain.article.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
@NoArgsConstructor
public abstract class Article {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "article_id")
    private Long id;
    private String title;
    @Lob
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
