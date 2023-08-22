package com.project.argo.domain.article.comment;

import com.project.argo.domain.Member;
import com.project.argo.domain.article.Article;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("A")
@Getter @NoArgsConstructor
public class Solution extends Comment{
    @Column(name = "vote_cnt")
    private int voteCnt;
    //TODO: 한 질문 내에서 투표할때 중복 안되게 하려면 어떻게 해야할까?
    @Builder
    public Solution(String content, Member author, Article article) {
        setContent(content);
        setAuthor(author);
        setArticle(article);
        this.voteCnt = 0;
    }
}
