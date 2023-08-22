package com.project.argo.service.team.study;

import com.project.argo.domain.team.study.Course;
import com.project.argo.domain.team.study.Mission;
import com.project.argo.domain.team.study.Study;
import com.project.argo.repository.team.study.CourseRepository;
import com.project.argo.repository.team.study.MissionRepository;
import com.project.argo.repository.team.study.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {
    private final StudyRepository studyRepository;
    private final CourseRepository courseRepository;
    private final MissionRepository missionRepository;
    @Transactional
    public void addCourseToStudy(Long studyId,Course course) {
        Study findStudy = studyRepository.findById(studyId).orElseThrow();
        courseRepository.save(course);
        findStudy.addCourse(course);
    }

    @Transactional
    public void addMissionToCourse(Long courseId, Mission mission) {
        Course findCourse = courseRepository.findById(courseId).orElseThrow();
        missionRepository.save(mission);
        findCourse.addMission(mission);
    }

    /**
     * 이중 dto로의 전환을 어떻게 할 것인가? => 요구 사항 그러니까 studyId로 조회할때, 따라오는 course랑 mission들 한꺼번에 조회
     * 하고 싶어.... 어떻게 해야할까나? study => course => mission
     * 1.일단 studyid로 course조회 하자
     * 2.그 다음에 courseid에 해당하는 모든  mission들 조회 가능? => 그러니까 기준이 course가 될거 같다.
     */
    public Study getStudyWithFetchAll(Long studyId) {

    }
}
