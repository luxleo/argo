package com.project.argo.repository.article.cond;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionSearchCond {
    private String title;

    @Builder
    public QuestionSearchCond(String title) {
        this.title = title;
    }
}
