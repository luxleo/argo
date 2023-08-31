package com.project.argo.response.article;

import com.project.argo.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class QuestionListResponse {
    private Long id;
    private String title;
    private String authorName;

    public QuestionListResponse(Long id, String title, String content, Member author) {
        this.id = id;
        this.title = title;
        this.authorName = author.getName();
    }
}
