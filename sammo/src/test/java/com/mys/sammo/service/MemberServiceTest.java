package com.mys.sammo.service;

import com.mys.sammo.model.General;
import com.mys.sammo.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void createMember() {

        Member creatingMember = memberService.createMember("test@gmail.com", "test");
        Member createdMember = memberService.findMember(creatingMember.getId());

        Assertions.assertEquals("test@gmail.com", createdMember.getEmail());
        Assertions.assertEquals("test", createdMember.getPassword());
    }

    @Test
    void createGeneral() {

        Member creatingMember = memberService.createMember("test@gmail.com", "test");
        General createdGeneral = memberService.createGeneral(creatingMember, "조조");

        Assertions.assertEquals("조조", createdGeneral.getName());
        Assertions.assertEquals(creatingMember, createdGeneral.getMember());
    }
}