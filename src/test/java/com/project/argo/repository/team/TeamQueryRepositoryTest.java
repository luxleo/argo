package com.project.argo.repository.team;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.Team;
import com.project.argo.repository.MemberQueryRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TeamQueryRepositoryTest {
    @Autowired
    TeamQueryRepository teamQueryRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    MapMemberTeamRepository mapRepository;
    @Autowired
    MemberQueryRepository memberQueryRepository;
    @Autowired
    EntityManager em;
    @BeforeEach
    void before() {
        Member m1 = Member.builder()
                .name("test1")
                .email("test1@gmail.com")
                .password("1234")
                .build();
        Member m2 = Member.builder()
                .name("test2")
                .email("test2@gmail.com")
                .password("1234")
                .build();
        Member m3 = Member.builder()
                .name("test3")
                .email("test3@gmail.com")
                .password("1234")
                .build();
        Member m4 = Member.builder()
                .name("test4")
                .email("test4@gmail.com")
                .password("1234")
                .build();
        em.persist(m1);em.persist(m2);em.persist(m3);em.persist(m4);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);em.persist(teamB);
        mapRepository.createMap(m1,teamA);mapRepository.createMap(m2,teamA);
        mapRepository.createMap(m3,teamB);mapRepository.createMap(m4,teamB);
    }

    @Test
    @DisplayName("회원은 팀에 가입 할 수있다.")
    void create_map() {
        Team teamA = teamRepository.findByName("teamA");
        List<Member> members = teamQueryRepository.findMembers(teamA.getId());
        List<String> names = members.stream()
                .map(m -> m.getName())
                .collect(Collectors.toList());

        assertThat(members.size()).isEqualTo(2);
        assertThat(names).containsExactly("test1", "test2");
    }

    @Test
    @DisplayName("회원은 복수의 팀에 가입 할 수있다.")
    void register_plural_teams() {
        //given
        Team teamB = teamRepository.findByName("teamB");
        Member test1 = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", "test1")
                .getResultList().stream().findAny().orElseThrow();
        mapRepository.createMap(test1, teamB);

        //when
        Team teamA = teamRepository.findByName("teamA");
        List<String> teamANames = teamQueryRepository.findMembers(teamA.getId()).stream()
                .map(m -> m.getName())
                .collect(Collectors.toList());
        List<String> teamBNames = teamQueryRepository.findMembers(teamB.getId()).stream()
                .map(m -> m.getName())
                .collect(Collectors.toList());
        //then
        assertThat(teamANames.size()).isEqualTo(2);
        assertThat(teamBNames.size()).isEqualTo(3);
        assertThat(teamBNames).contains("test1");

        List<Team> teams = memberQueryRepository.findTeams(test1.getId());
        List<String> teamNames = teams.stream()
                .map(t -> t.getName())
                .collect(Collectors.toList());
        assertThat(teamNames.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("회원은 팀에서 탈퇴 할 수있고, 팀장은 팀에서 팀원을 내보낼수있다.")
    void remove_member_from_team() {
        //given
        Member findMember = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", "test1")
                .getResultList().stream().findAny().orElseThrow();
        Long teamAId = teamRepository.findByName("teamA").getId();

        mapRepository.removeMemberFromTeam(findMember.getId(), teamAId);
        //when
        List<Member> members = teamQueryRepository.findMembers(teamAId);
        List<String> names = members.stream()
                .map(m -> m.getName())
                .collect(Collectors.toList());
        //then
        assertThat(names.size()).isEqualTo(1);
        assertThat(names).containsExactly("test2");
    }

}