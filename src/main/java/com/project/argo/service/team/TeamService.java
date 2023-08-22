package com.project.argo.service.team;


import com.project.argo.repository.team.TeamQueryRepository;
import com.project.argo.repository.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {
    private final TeamQueryRepository teamQueryRepository;
    private final TeamRepository teamRepository;
}
