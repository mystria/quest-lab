package com.mys.sammo;

import com.mys.sammo.model.Territory;
import com.mys.sammo.model.enums.Region;
import com.mys.sammo.model.enums.TerritoryScale;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDb {

    private final InitDbService initDbService;

    @PostConstruct
    public void init() {

        initDbService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class InitDbService {

        private final EntityManager em;

        public void dbInit1() {

            Territory territory1 = Territory.setup("낙양", TerritoryScale.CAPITAL, Region.중원);
            em.persist(territory1);
            Territory territory2 = Territory.setup("업", TerritoryScale.CAPITAL, Region.하북);
            em.persist(territory2);
        }
    }
}
