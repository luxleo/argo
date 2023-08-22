package com.project.argo.repository.presentation;

import com.project.argo.domain.team.study.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.argo.domain.team.study.QCourse.*;
import static com.project.argo.domain.team.study.QMission.*;
import static com.project.argo.domain.team.study.QStudy.*;

@Repository
@RequiredArgsConstructor
public class StudyWindowRepository {
    private JPAQueryFactory query;

    /**
     * 1. studyId에 해당하는 Course들을 조회한다.
     * 2. 각 courseId에 해당하는 mission들을 조회한다.
     * 3. 2의 결과를 1에 할당하고 1의 결과를 study courses에 할당한다.
     * json api spec => {
     *     studyId: "",
     *     courses:[
     *          {courseId:"",
     *          courseTItle:"",
     *          missions:[
     *          {
                    missionId:"",
     *              missionContent:""
     *              }
     *          ]}
     *     ]
     * }
     */
    public Study getStudyWithFetchAll(Long studyId) {
        List<Course> courses = query.select(course)
                .from(course)
                .join(course.study, study).fetchJoin()
                .where(course.study.id.eq(studyId))
                .fetch();

        List<Long> courseIds = courses.stream()
                .map(c -> c.getId())
                .collect(Collectors.toList());

        List<Mission> missions = query.selectFrom(mission)
                .join(mission.course, course).fetchJoin()
                .where(mission.course.id.in(courseIds))
                .fetch();
    }
}
