package com.mys.sammo.model;

import com.mys.sammo.model.enums.Region;
import com.mys.sammo.model.enums.TerritoryScale;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Territory {

    @Id
    @GeneratedValue
    @Column(name = "territory_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TerritoryScale scale;

    @Enumerated(EnumType.STRING)
    private Region region;

//    @Embedded
//    private Indicator population;
//
//    @Embedded
//    private Indicator morale;
//
//    @Embedded
//    private Indicator agriculture;
//
//    @Embedded
//    private Indicator commerce;
//
//    @Embedded
//    private Indicator rampart;
//
//    @Embedded
//    private Indicator defense;
//
//    @Embedded
//    private Indicator security;

    private int market;

    @ManyToOne
    @JoinColumn(name = "kingdom_id")
    private Kingdom kingdom;

    @OneToMany(mappedBy="position")
    private List<General> generals;

    public static Territory setup(String name, TerritoryScale scale, Region region) {

        Territory territory = new Territory();
        territory.name = name;
        territory.scale = scale;
        territory.region = region;
        territory.generals = new ArrayList<>();
        return territory;
    }

    public void assignedBy(General general) {

        this.generals.add(general);
        general.moveIn(this);
    }

    public void occupiedBy(Kingdom kingdom) {

        this.kingdom = kingdom;
    }
}
