package com.project.argo.repository;

import com.project.argo.domain.team.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.argo.domain.team.QMapMemberTeam.mapMemberTeam;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {
    private final JPAQueryFactory query;

    public List<Team> findTeams(Long memberId) {
        return query.select(mapMemberTeam.team)
                .from(mapMemberTeam)
                .where(mapMemberTeam.member.id.eq(memberId))
                .fetch();
    }
}
