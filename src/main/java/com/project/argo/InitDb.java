package com.project.argo;

import com.project.argo.domain.team.project.recruit.Position;
import com.project.argo.domain.team.project.recruit.PositionCategory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    void init() {
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        /**
         * position category랑 position 정해두기(front(web,app),backend(server,blockChain))
         */
        public void init() {
            PositionCategory front = new PositionCategory("프론트");
            PositionCategory backend = new PositionCategory("백엔드");

            Position pos1 = new Position("웹 프론트엔드");
            Position pos2 = new Position("안드로이드");
            Position pos3 = new Position("웹서버");
            Position pos4 = new Position("블록체인");

            front.addPosition(pos1);
            front.addPosition(pos2);
            backend.addPosition(pos3);
            backend.addPosition(pos4);

            em.persist(front);
            em.persist(backend);
        }
    }
}
