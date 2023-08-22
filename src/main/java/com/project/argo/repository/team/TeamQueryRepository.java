package com.project.argo.repository.team;

import com.project.argo.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.argo.domain.team.QMapMemberTeam.mapMemberTeam;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TeamQueryRepository {
    private final JPAQueryFactory query;

    public List<Member> findMembers(Long teamId) {
        return query.select(mapMemberTeam.member)
                .from(mapMemberTeam)
                .where(mapMemberTeam.team.id.eq(teamId))
                .fetch();
    }
}
