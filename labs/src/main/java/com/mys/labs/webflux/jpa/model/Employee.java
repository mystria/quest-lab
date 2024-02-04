package com.mys.labs.webflux.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String name;

    @Column(unique = true)
    private String email;

    public static Employee create(String name, String email) {

        Employee employee = new Employee();
        employee.name = name;
        employee.email = email;
        return employee;
    }

    public Employee join(Company company) {

        this.company = company;
        return this;
    }
}
