# USECASE
## project
### 1.방 생성
- 방장이 프로젝트를 생성한다. 생성할때 -> 포지션 및 포지션 별 인원수를 정한다=>(stage1: 처음에는 스택 명시 없이 그냥 진행, stage2: 기술스택 명시). -> 프로젝트 설명을 한다.(사진 첨부 가능하게? -> editor 필요할거 같다.)
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

## 도메인-position
- Position:
    - 설명: 프로젝트 모임 생성시 멤버들이 맡을 포지션이다.
    - 엔티티 관계: PositionCategory -> Position (ex: {'백엔드': ['api 개발','디비 담당','배포서버 설정']})
    - 용례: 
        - 1.모임 만들때: 방장이 모임을 생성할 때 ProjectGroup으로 만든다. 이떄 ProjectGroup은 Position과 연관관계를 가진다.
        - 2.모임 지원할때: 해당 프로젝트 팀에 가입하는 멤버는 Project그룹의 roles Array와 연관관계를 가진다.
            즉, 멤버는 프로젝트 팀 내에서 Role로 wrapping된다. ProjectGroup 생성시 정원인 limit필드를 넘어서 등록할 수 없다.
    - 프로젝트 생성순서: 
        - 1. ProjectInfo 작성 -> ProjectGroup들 생성(생성시 Position 할당한다.) ->
## 도메인-stage,job,task(를 바라 보는 관점)
- Stage: 포스트로 따지면 일종의 카테고리로 바라보기. => worker와 직접적인 연관을 가지지 않는다.
- Job: 실제적인 할당 작업이다. => worker와 직접적인 관련이 있다.
- Task: 일종의 댓글이랄까 job에 종속된 채로 있다.

# Redis 사용할 명분? (stage1. 세션으로 먼저 구현한다. => stage2. 이후에 시도 해보자 기본 기능 다 구현후에)
## 유저 회원가입시 
- 1. 방법: 유저가 입력한 이메일로 jwt키 생성하여 redis db에 코드 등록하고, 유저가 입력한 정보들도 함께 json형식으로 저장한다(***redis-hashes*** 자료구조로 해보자.) 
    그리고 이메일 값으로 쿠키 생성하여 클라이언트단에 보낸다.
- 2. 이메일 검증 화면으로 리다이렉트하고(중복해서 날리는 요청 방지), 검증화면에서 입력한 인증코드 값이 전송될때, 함께 전송되는 쿠키의 키 값으로 redis db에 등록된 verification code와 비교한다.
- 3. 맞으면 유저를 db에 저장한다. + 해당 이메일 키 값은 30분 유효기간을 준다. 
- 4. 다른 솔루션: db에 임의로 생성해두고 deactivate로 필드에 할당한다. => 그런데 인증 못 하여 생긴 쓰레기 값들을 batch등을 이용하여 따로 관리해야하지 않나?
## 유저 비밀번호 찾기 솔루션
- 1. 방법: jwt key생성하여 쿠키에 실어 보내준다. 생성된 key로 redis db에 인증번호를 등록한다+ 이메일로 전송. 이후 클라이언트가 보낸 jwt key쿠키와 받아 값을 비교한다.
    만일 같으면 쿠키를 파괴한다. => 유저가 중복하여 클릭하는 경우도 쿠키에 해당 jwt키 값이 포함되어있으면 prevent하는 식으로 가면 어떨까?