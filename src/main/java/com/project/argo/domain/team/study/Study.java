package com.project.argo.domain.team.study;

import com.project.argo.domain.team.Team;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("study")
@NoArgsConstructor
public class Study extends Team {
    //TODO: 스터디에 과목 추가하기(과목 필드: 이름, 설명, 사진,그룹(백엔드,프론트엔드 시스템등)
    @Column(name = "study_title")
    private String title;
    @Lob
    private String desc;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL,mappedBy = "study")
    private List<Course> courses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StudyStatus studyStatus;
    @Builder
    public Study(String name, String title, String desc) {
        super(name);
        this.studyStatus = StudyStatus.ON;
        this.title = title;
        this.desc = desc;
    }
    public void addCourse(Course course) {
        this.getCourses().add(course);
        course.setStudy(this);
    }
}
