package com.mys.sammo.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Kingdom {

    @Id
    @GeneratedValue
    @Column(name = "kingdom_id")
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "capital_id")
    private Territory capital;

    @OneToMany(mappedBy="kingdom")
    private List<Territory> territories;

    @OneToOne
    @JoinColumn(name = "force_id")
    private Force force;

    @Embedded
    private Indicator technology;

    public static Kingdom found(String name, Territory capital, Force force) {

        Kingdom kingdom = new Kingdom();
        kingdom.name = name;
        kingdom.capital = capital;
        kingdom.territories = new ArrayList<>();
        kingdom.territories.add(capital);
        kingdom.force = force;
        kingdom.technology = new Indicator();
        capital.occupiedBy(kingdom);
        return kingdom;
    }

    public void occupy(Territory territory) {

        territories.add(territory);
        territory.occupiedBy(this);
    }
}
