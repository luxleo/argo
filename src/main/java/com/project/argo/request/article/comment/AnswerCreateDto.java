package com.project.argo.request.article.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerCreateDto {
    private Long authorId;
    private Long articleId;
    private String content;
    @Builder
    public AnswerCreateDto(Long authorId, Long articleId, String content) {
        this.authorId = authorId;
        this.articleId = articleId;
        this.content = content;
    }
}
