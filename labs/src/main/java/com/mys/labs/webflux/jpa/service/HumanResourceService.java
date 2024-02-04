package com.mys.labs.webflux.jpa.service;

import com.mys.labs.webflux.jpa.model.Company;
import com.mys.labs.webflux.jpa.model.Employee;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class HumanResourceService {

    private final EntityManager em;

    public Long createCompany(String name) {

        Company company = Company.create(name);
        em.persist(company);
        return company.getId();
    }

    public Company findCompany(Long companyId) {

        return em.find(Company.class, companyId);
    }

    public Long createEmployee(Long companyId, String name, String email) {

        Employee employee = Employee.create(name, email);
        Company company = em.find(Company.class, companyId);
        employee.join(company);
        em.persist(employee);
        return employee.getId();
    }

    public List<Employee> listEmployees(Long companyId) {

        return em.createQuery("select e from Employee e where e.company.id = :companyId", Employee.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }
}
