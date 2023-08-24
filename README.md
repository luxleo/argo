# USECASE
## project
### 1.방 생성
- 방장이 프로젝트를 생성한다. 생성할때 -> 포지션 및 포지션 별 인원수를 정한다. -> 프로젝트 설명을 한다.(사진 첨부 가능하게? -> editor 필요할거 같다.)
- 방 생성시 사용할 기술 스택도 정한다.(stage1: 프로젝트 단위로 뭉쳐서 시행, stage2: 포지션별 실행)
- 포지션 도메인 그룹(인원제한이 있도록 수정)

### 2.유저 프로젝트에 지원(포지션)
- 유저가 프로젝트 지원 신청시, 지원할 포지션(가용 인원이 있을때) 함께 선택하여 제출
- 방장이 수락(stage1: 그냥 요청사항에 넣기, stage2: 알림-[카카오나 메일])
- 수락하면 지원할 포지션 가용인원 감소시키고, 포지션에 지원한 유저 등록

## 수정사항
### project 
- 포지션 개요: => 1. 프로젝트에 포지션에 그룹이 필요함(프론트엔드,백엔드,시스템...) => 그룹별 인원제한이 있음.
- 그룹별 구현체: => {"프론트엔드":[웹,안드로이드,ios],"백엔드":[웹서버,dba?],"시스템":[리눅스,aws등]}
- 탑다운 방식으로 가보자
    프로젝트 => 포지션 그룹 => 포지션 =>
```text

PositionCategory{
    @Column(name="group_name")
    groupName: ["프론트엔드","백엔드","시스템"]
    
    @OneToMany(cascade=ALL, orphan = true)
    private List<Position> positions = new ArrayList<>();
}
Position{
    @ManyToOne
    @JoinColumn(name = "group_name")
    private WorkGroup workgroup;
    
    private String name;
}
Role{
    @ManyToOne
    @JoinColumn(name="position_id")
    private Position position;
    
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
}
argo{
    List<ProjectGroup> projectGroup;
}
ProjectGroup{
    @ManyToOne
    @JoinColumn(name="team_id")
    private Project project;
    
    private Integer limit;
    
    @OneToMany(orphan=true,cascade=ALL)
    private List<Role> roles;
    
    @Enumerated(String)
    private RecruitStatus recruitStatus;
}
RecruitStatus{
    // HIRING: 모집중인 상태, FULL: 다 모집한 상태
    HIRING,FULL
}
```
request.parameterName("[name]")으로 같은 키의 여러값을 받을 수 없다. ex => name=lee&name=dragon 인 경우 맨 처음 것만 받는다.
    