package com.mys.labs.webflux.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    private String name;

    public static Company create(String name) {

        Company company = new Company();
        company.name = name;
        return company;
    }
}
