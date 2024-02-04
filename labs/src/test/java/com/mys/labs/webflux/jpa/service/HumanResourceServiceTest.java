package com.mys.labs.webflux.jpa.service;

import com.mys.labs.webflux.jpa.model.Employee;
import jakarta.persistence.EntityManager;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class HumanResourceServiceTest {

    @Autowired
    HumanResourceService humanResourceService;

    @Autowired
    EntityManager em;

    @Test
    void failCreateEmployees() {

        Long companyId = humanResourceService.createCompany("Samsung Electronics");
        humanResourceService.createEmployee(companyId, "조조", "jojo@samsung.com");
        humanResourceService.createEmployee(companyId, "조조2", "jojo@samsung.com");

        assertThrows(ConstraintViolationException.class,
                () -> humanResourceService.listEmployees(companyId));
    }

    @Test
    void listEmployees() {

        Long companyId = humanResourceService.createCompany("Samsung Electronics");
        humanResourceService.createEmployee(companyId, "조조", "jojo@samsung.com");
        humanResourceService.createEmployee(companyId, "유비", "ubi@samsung.com");
        List<Employee> employees = humanResourceService.listEmployees(companyId);

        assertEquals(2, employees.size());
        assertEquals("조조", employees.get(0).getName());
        assertEquals("유비", employees.get(1).getName());
    }

}