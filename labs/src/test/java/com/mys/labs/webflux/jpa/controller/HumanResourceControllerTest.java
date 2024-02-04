package com.mys.labs.webflux.jpa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class HumanResourceControllerTest {

    @Autowired
    HumanResourceController humanResourceController;

    @Test
    void listEmployees() {
        assertTrue(CollectionUtils.isEmpty(humanResourceController.listEmployees(1L).getBody()));
    }
}