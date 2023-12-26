package com.mys.sammo.service;

import com.mys.sammo.model.Force;
import com.mys.sammo.model.General;
import com.mys.sammo.model.Kingdom;
import com.mys.sammo.model.enums.Faith;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KingdomCommandService {

    private final EntityManager em;

    public Force foundForce(General general, Faith faith) {

        Force force = Force.raise(general, faith);
        em.persist(force);
        return force;
    }

    public Kingdom foundKingdom(General general, String name) {

        Kingdom kingdom = Kingdom.found(name, general.getPosition(), general.getForce());
        em.persist(kingdom);
        return kingdom;
    }
}
