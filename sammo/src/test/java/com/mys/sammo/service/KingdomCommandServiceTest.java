package com.mys.sammo.service;

import com.mys.sammo.model.Force;
import com.mys.sammo.model.General;
import com.mys.sammo.model.Kingdom;
import com.mys.sammo.model.Member;
import com.mys.sammo.model.enums.Faith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class KingdomCommandServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    KingdomCommandService kingdomCommandService;

    @Test
    void foundForce() {

        Member creatingMember = memberService.createMember("test@gmail.com", "test");
        General general = memberService.createGeneral(creatingMember, "조조");
        Force force = kingdomCommandService.foundForce(general, Faith.묵가);

        Assertions.assertEquals(Faith.묵가, force.getFaith());
        Assertions.assertEquals(general, force.getLeader());
        Assertions.assertEquals(1, force.getGenerals().size());
    }

    @Test
    void foundKingdom() {

        Member creatingMember = memberService.createMember("test@gmail.com", "test");
        General general = memberService.createGeneral(creatingMember, "조조");
        kingdomCommandService.foundForce(general, Faith.묵가);
        Kingdom kingdom = kingdomCommandService.foundKingdom(general, "위");

        Assertions.assertEquals("위", kingdom.getName());
        Assertions.assertEquals(general.getPosition(), kingdom.getCapital());
        Assertions.assertEquals(general.getForce(), kingdom.getForce());
        Assertions.assertEquals(1, kingdom.getTerritories().size());
    }
}