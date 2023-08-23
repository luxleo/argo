package com.project.argo.service.team.study;

import com.project.argo.domain.team.study.Course;
import com.project.argo.domain.team.study.Mission;
import com.project.argo.domain.team.study.Study;
import com.project.argo.repository.team.study.CourseRepository;
import com.project.argo.repository.team.study.MissionRepository;
import com.project.argo.repository.team.study.StudyRepository;
import com.project.argo.request.team.study.CourseUpdateRequest;
import com.project.argo.response.team.study.StudyDetailResponse;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class StudyServiceTest {
    @Autowired
    StudyService studyService;
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    MissionRepository missionRepository;
    @Autowired
    EntityManager em;

    @Transactional
    @BeforeEach
    void before() {
        Study studyA = Study.builder()
                .name("studyA")
                .title("test title")
                .build();
        em.persist(studyA);

        Course courseA = Course.builder()
                .name("courseA")
                .build();
        Course courseB = Course.builder()
                .name("courseB")
                .build();
        studyService.addCourseToStudy(studyA.getId(), courseA);
        studyService.addCourseToStudy(studyA.getId(), courseB);

        Mission mission1 = new Mission("mission1");
        Mission mission2 = new Mission("mission2");
        Mission mission3 = new Mission("mission3");
        Mission mission4 = new Mission("mission4");

        studyService.addMissionToCourse(courseA.getId(), mission1);
        studyService.addMissionToCourse(courseA.getId(), mission2);
        studyService.addMissionToCourse(courseB.getId(), mission3);
        studyService.addMissionToCourse(courseB.getId(), mission4);
    }

    @Test
    @DisplayName("study의 detail 다 가져오기")
    void getStudyWithDetail() {
        //given
        Study findStudy = studyRepository.findAll()
                .stream().findAny().orElseThrow();
        //when
        StudyDetailResponse result = studyService.getStudyWithAllDetails(findStudy.getId());
        //then
        assertThat(result.getCourses().size()).isEqualTo(2);
        assertThat(result.getCourses().get(0).getMissions().size()).isEqualTo(2);
        assertThat(result.getCourses().get(1).getMissions().size()).isEqualTo(2);
    }

    /**
     * 좀 많이 해맨 테스트 케이스 트랜잭션을 따로 분리하여도 course는 삭제가 안된다.
     */
    @Test
    @Transactional
    @Commit
    @DisplayName("course 삭제시 연관된 mission들도 삭제")
    void removed_together() {
        //given
        Course courseA = courseRepository.findByName("courseA").get();
        //when
        delete_course_indenpendent(courseA.getId());
        em.clear();
        //then
        List<Mission> missions = missionRepository.findAll();
        assertThat(missions.size()).isEqualTo(2);

        Course findCourseA = courseRepository.findByName("courseA").get();
        assertThat(findCourseA.getName()).isEqualTo("courseA");
    }

    @Test
    void update_course() {
        //given
        Course courseA = courseRepository.findByName("courseA").orElseThrow();
        CourseUpdateRequest dto = new CourseUpdateRequest();
        dto.setCourseId(courseA.getId());
        dto.setName("dragon");
        //when
        studyService.updateCourseWithDto(dto);
        em.flush();
        em.clear();
        //then
        Course updatedA = courseRepository.findByName("dragon").orElseThrow();
        assertThat(updatedA).isNotNull();
        assertThat(updatedA.getName()).isEqualTo("dragon");
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void delete_course_indenpendent(Long courseId) {
        courseRepository.deleteById(courseId);
        em.flush();
    }
}