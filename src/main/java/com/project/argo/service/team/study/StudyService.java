package com.project.argo.service.team.study;

import com.project.argo.domain.team.study.Course;
import com.project.argo.domain.team.study.Mission;
import com.project.argo.domain.team.study.Study;
import com.project.argo.repository.presentation.StudyWindowRepository;
import com.project.argo.repository.team.study.CourseRepository;
import com.project.argo.repository.team.study.MissionRepository;
import com.project.argo.repository.team.study.StudyRepository;
import com.project.argo.request.team.study.CourseUpdateRequest;
import com.project.argo.request.team.study.MissionUpdateRequest;
import com.project.argo.response.team.study.StudyDetailResponse;
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
    private final StudyWindowRepository studyWindowRepository;
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

    @Transactional
    public void updateCourseWithDto(CourseUpdateRequest dto) {
        Course findCourse = courseRepository.findById(dto.getCourseId()).orElseThrow();
        findCourse.updateWithDto(dto);
    }

    @Transactional
    public void updateMissionWithDto(MissionUpdateRequest dto) {
        Mission findMission = missionRepository.findById(dto.getId()).orElseThrow();
        findMission.updateWithDto(dto);
    }

    @Transactional
    public void removeMission(Long missionId) {
        missionRepository.deleteById(missionId);
    }

    @Transactional
    public void removeCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
    /**
     * study의 모든 course, course별 mission을 조회 하기 위한 서비스
     */
    public StudyDetailResponse getStudyWithAllDetails(Long studyId) {
        StudyDetailResponse findStudy = studyWindowRepository.getStudyWithFetchAll(studyId);
        return findStudy;
    }
}
