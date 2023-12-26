package com.mys.sammo.service;

import com.mys.sammo.model.General;
import com.mys.sammo.model.Member;
import com.mys.sammo.model.Territory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final EntityManager em;

    public Member createMember(String email, String password) {

        Member member = Member.create(email, password);
        em.persist(member);
        return member;
    }

    public Member findMember(Long memberId) {

        return em.find(Member.class, memberId);
    }

    public General createGeneral(Member member, String name) {

        Territory position = em.find(Territory.class, 1L);
        General general = General.showUp(member, name, position);
        em.persist(general);
        return general;
    }

    public General findGeneral(Long generalId) {

        return em.find(General.class, generalId);
    }
}
