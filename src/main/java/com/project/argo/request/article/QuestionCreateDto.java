package com.project.argo.request.article;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class QuestionCreateDto {
    private String title;
    private String content;
    private Long memberId;
    @Builder
    public QuestionCreateDto(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }
}
