package com.project.argo.repository.presentation;

import com.project.argo.domain.team.study.*;
import com.project.argo.response.team.study.CourseDetailDto;
import com.project.argo.response.team.study.StudyDetailResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.project.argo.domain.team.study.QCourse.*;
import static com.project.argo.domain.team.study.QMission.*;
import static com.project.argo.domain.team.study.QStudy.*;

@Repository
@RequiredArgsConstructor
public class StudyWindowRepository {
    private final JPAQueryFactory query;

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
    public StudyDetailResponse getStudyWithFetchAll(Long studyId) {
        List<CourseDetailDto> courses = query.select(course)
                .from(course)
                .join(course.study, study).fetchJoin()
                .where(course.study.id.eq(studyId))
                .fetch().stream()
                .map(CourseDetailDto::new)
                .collect(Collectors.toList());

        List<Long> courseIds = courses.stream()
                .map(c -> c.getId())
                .collect(Collectors.toList());

        Map<Long, List<Mission>> missionGroup = query.selectFrom(mission)
                .join(mission.course, course).fetchJoin()
                .where(mission.course.id.in(courseIds))
                .fetch().stream()
                .collect(Collectors.groupingBy(Mission::getCourseId));

        for (CourseDetailDto course : courses) {
            List<Mission> missions = missionGroup.get(course.getId());
            course.addMissions(missions);
        }

        return StudyDetailResponse.builder()
                .studyId(studyId)
                .courses(courses)
                .build();
    }
}
