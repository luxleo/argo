package com.project.argo.domain.article;

import com.project.argo.domain.Member;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* TODO:
*  1. 조회수 조회 가능하게
*  2. 투표 가장 높은 녀석 어떻게 처리 할지
*  3. BaseEntity 상속 받아서 register,updated date 같은거 잘 처리해주자.
* */
@Entity
@DiscriminatorValue("Q")
@Getter @NoArgsConstructor
public class Question extends Article{
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @Builder
    public Question(String title, String content,Member member) {
        setTitle(title);
        setAuthor(member);
        setContent(content);
        this.status = QuestionStatus.UNSOLVED;
    }
}
