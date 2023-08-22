package com.project.argo.repository.team;

import com.project.argo.domain.Member;
import com.project.argo.domain.team.MapMemberTeam;
import com.project.argo.domain.team.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.project.argo.domain.team.QMapMemberTeam.mapMemberTeam;

@Repository
@RequiredArgsConstructor
public class MapMemberTeamRepository {
    private final JPAQueryFactory query;
    private final EntityManager em;

    @Transactional
    public void createMap(Member member, Team team) {
        MapMemberTeam map = new MapMemberTeam();
        map.createMapping(member, team);
        em.persist(map);
    }

    @Transactional
    public void removeMemberFromTeam(Long memberId,Long teamId) {
        query.delete(mapMemberTeam)
                .where(mapMemberTeam.team.id.eq(teamId), mapMemberTeam.member.id.eq(memberId))
                .execute();
    }

    @Transactional
    public void removeTeamFromMember(Long memberId, Long teamId) {
        query.delete(mapMemberTeam)
                .where(mapMemberTeam.team.id.eq(teamId), mapMemberTeam.member.id.eq(memberId))
                .execute();
    }
}
