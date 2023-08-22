package com.project.argo.repository.team.study;

import com.project.argo.domain.team.study.QCourse;
import com.project.argo.domain.team.study.QStudy;
import com.project.argo.domain.team.study.Study;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.project.argo.domain.team.study.QCourse.*;
import static com.project.argo.domain.team.study.QStudy.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StudyQueryRepository {
    private final JPAQueryFactory query;

    public Study studyDetail(Long studyId) {
        return query.select(course.study, course)
                .from(course)
                .join(course.study,study)
                .where(course.study.id.eq(studyId))

    }

}
